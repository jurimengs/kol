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

import net.sf.json.JSONObject;

import com.org.Connection;
import com.org.model.reflect.ReflectDbModel;
import com.org.services.DataSourceContainer;
import com.org.util.StringUtil;
import com.org.utils.SmpPropertyUtil;

public class BaseDao {
	protected java.sql.Connection getConnection(){
		// 这里的连接不用管连接数，连接数是由数据源管理的
		Connection conn = null;
		conn = DataSourceContainer.getInstance().getConnection(SmpPropertyUtil.getValue("identify_db_relation", "kol"));
		java.sql.Connection res = (java.sql.Connection)conn.getRealConnection();
		return res;
	}
	
	protected PreparedStatement prepareStatement(String sql, Connection conn) {
		PreparedStatement ps = null;
		try {
			ps = ((java.sql.Connection)conn.getRealConnection()).prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}

	protected static <T> List<T> parseResultSet(ResultSet rs, T entity)
			throws SQLException, IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		List<T> list = new ArrayList<T>();
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
						m = entity.getClass().getDeclaredMethod("set" + model.getKey(),
								model.getValue().getClass());
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
		return list;
	}

	protected static List<JSONObject> parseResultSetToJSON(ResultSet rs, boolean collumToUpper)
			throws SQLException, IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		List<JSONObject> list = new ArrayList<JSONObject>();
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
		return list;
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
	
	protected ResultSet getResultSet(String sql, 
		Map<Integer, Object> params) throws SQLException{
		PreparedStatement ps = getConnection().prepareStatement(sql);
		setStatmentParams(ps, params);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	protected void setStatmentParams(PreparedStatement ps, Map<Integer, Object> params) throws SQLException{
		for (Iterator<Integer> iterator = params.keySet().iterator(); iterator
				.hasNext();) {
			Integer key = iterator.next();
			ps.setObject(key, params.get(key));
		}
	}
}
