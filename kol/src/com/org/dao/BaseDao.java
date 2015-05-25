package com.org.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.org.Connection;
import com.org.model.reflect.ReflectDbModel;
import com.org.services.DataSourceContainer;
import com.org.util.StringUtil;
import com.org.utils.SmpPropertyUtil;

public class BaseDao {
	
	@SuppressWarnings("unchecked")
	public static Connection<java.sql.Connection> conn = (Connection<java.sql.Connection>) DataSourceContainer.getInstance().getConnection(SmpPropertyUtil.getValue("identify_db_relation", "kol"));
	protected java.sql.Connection getConnection(){
		java.sql.Connection res = (java.sql.Connection)conn.getRealConnection();
		return res;
	}

	protected <T> List<T> queryListByT(String sql, Map<Integer, Object> params, T entity)
			throws SQLException, IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		
		java.sql.Connection connection = null;
		List<T> list = new ArrayList<T>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			connection = getConnection();
			ps = connection.prepareStatement(sql);
			setStatmentParams(ps, params);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			// 列数
			int columnCounts = rsmd.getColumnCount();
			//
			ReflectDbModel model = new ReflectDbModel();
			Method m = null;
			while (rs.next()) {
				// 这个地方相当于每用一次就new一次,否则数据会覆盖上一次的数据
				for (int i = 1; i <= columnCounts; i++) {
					initReflectDbModel(rs, model, i);
					if (model.getValue() != null && model.getValue() != "") {
						try {
							m = entity.getClass().getDeclaredMethod("set" + model.getKey(), model.getValue().getClass());
							m.invoke(entity, model.getValue());
						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							System.out.println(e.getMessage() + ": NoSuchMethodException");
						}
					}
				}
				list.add(entity);
			}
		}finally{
			releaseAll(rs, ps, connection);
		}			
		return list;
	}
	
	/**
	 * 
	 * @param sql
	 * @param params
	 * @param collumToUpper 是否遵守驼峰
	 * @return
	 * @throws SQLException
	 */
	protected JSONArray queryList(String sql, Map<Integer, Object> params, boolean collumToUpper) throws SQLException{
		JSONArray list = new JSONArray();
		java.sql.Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			connection = getConnection();
			ps = connection.prepareStatement(sql);
			if(params != null){
				setStatmentParams(ps, params);
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			// 列数
			int columnCounts = rsmd.getColumnCount();
			//
			JSONObject jo = null;
			while (rs.next()) {
				jo = new JSONObject();
				for (int i = 1; i <= columnCounts; i++) {
					String key = rsmd.getColumnName(i);
					//  转实例名
					key = StringUtil.toEntityName(rsmd.getColumnName(i), collumToUpper);
					Object value = rs.getObject(i);
					value = (value == null) ? "" : value.toString();
					jo.put(key, value);
				}
				list.add(jo);
			}
		}finally{
			releaseAll(rs, ps, connection);
		}			
		return list;		
	}

	private void releaseAll(ResultSet rs, PreparedStatement ps,
			java.sql.Connection connection) throws SQLException {
		if(rs != null){
			rs.close();
		}
		if(ps != null){
			ps.close();
		}
		conn.close(connection);
	}

	protected JSONArray queryList(String sql, Map<Integer, Object> params) throws SQLException{
		return queryList(sql, params, true);		
	}
	
	protected void setStatmentParams(PreparedStatement ps, Map<Integer, Object> params) throws SQLException{
		for (Iterator<Integer> iterator = params.keySet().iterator(); iterator
				.hasNext();) {
			Integer key = iterator.next();
			ps.setObject(key, params.get(key));
		}
	}

	protected static void initReflectDbModel(ResultSet rs, ReflectDbModel model,
			int index) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		String key = rsmd.getColumnName(index);
		// 转实例名
		key = StringUtil.toEntityName(rsmd.getColumnName(index), false);
		
		String paramType = rsmd.getColumnClassName(index);
		Object value = rs.getObject(index);

		// 首字母大写
		key = String.valueOf(key.charAt(0)).toUpperCase() + key.substring(1);
		paramType = paramType.substring(paramType.lastIndexOf(".") + 1);

		model.setKey(key);
		model.setParamType(paramType);
		model.setValue(value);
	}
}
