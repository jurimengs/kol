package com.org.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.org.exception.SvcException;

@Repository
public class CommonDao extends BaseDao {
	public JSONObject querySingle(String sql, 
			Map<Integer, Object> params) throws SvcException{
		JSONObject jo = null;
		try {
			ResultSet rs = getResultSet(sql, params);
			List<JSONObject> list = parseResultSetToJSON(rs, true);
			if(list.size() > 1){
				throw new SvcException("Common Dao : result counts more than single");
			}
			if (list.size() <= 0) {
				return null;
			}
			return list.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return jo;
	}
	
	public <T> T querySingle(Class<T> entityClass, String sql, 
			Map<Integer, Object> params) throws SvcException {
		// 反射定义的T对象
		T entity = null;
		try {
			entity = entityClass.newInstance();
//			PreparedStatement ps = getConnection().prepareStatement(sql);
//			setStatmentParams(ps, params);
//			ResultSet rs = ps.executeQuery();
			ResultSet rs = getResultSet(sql, params);
			List<T> list = parseResultSet(rs, entity);
			if (list.size() > 1) {
				throw new SvcException("Common Dao : result counts more than single");
			}
			if (list.size() <= 0) {
				return null;
			}
			return list.get(0);
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param entityClass
	 * @param sql
	 * @param params ?,?,? {1:"...", 2:"...", 3:"..."}
	 * @return
	 */
	public <T> List<T> queryList(Class<T> entityClass, String sql, 
			Map<Integer, Object> params) {
		// 反射定义的T对象
		T entity = null;
		try {
			entity = entityClass.newInstance();
//			PreparedStatement ps = getConnection().prepareStatement(sql);
//			setStatmentParams(ps, params);
			ResultSet rs = getResultSet(sql, params);
			return parseResultSet(rs, entity);
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

//	public void addSingle(Object obj){
//		try {
//			Entity a = obj.getClass().getAnnotation(Entity.class);
//			String tableName = a.tableName();
//			String sql = "insert into "+tableName+" values (?)";
////			PreparedStatement ps = con.prepareStatement(sql);
////			PreparedStatement ps = con.prepareStatement(sql, new Class[]{String.class}, id);
////			ps.execute();
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
	
	/**
	 * 
	 * @param sql
	 * @param params ?,?,? {1:"...", 2:"...", 3:"..."}
	 * @return 
	 * @return
	 * @throws SQLException 
	 * @throws SvcException 
	 */
	public synchronized <T> void addSingle(String sql, Map<Integer, Object> params) throws SQLException {
		java.sql.Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			setStatmentParams(ps, params);
		} catch (SQLException e1) {
			e1.printStackTrace();
			ps.close();
			return;
		}
		try {
			ps.execute();
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			ps.close();
			return;
		}
	}
	
	public synchronized <T> void update(String sql, Map<Integer, Object> params) throws SQLException {
		java.sql.Connection conn = getConnection();
		
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			setStatmentParams(ps, params);
		} catch (SQLException e1) {
			e1.printStackTrace();
			ps.close();
			return;
		}
		try {
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			ps.close();
			return;
		}
	}
	// 不建议使�?处理过于复杂
//	private  Map<String, Object> parseObjToTableProperties(Object obj) throws IllegalArgumentException, IllegalAccessException{
//		Field[] fds = obj.getClass().getDeclaredFields();
//		Method md = null;
//		Map<String, Object> result = new HashMap<String, Object>();
//		for (int i = 0; i < fds.length; i++) {
//			try {
//				String fdName = fds[i].getName();
//				fdName = String.valueOf(fdName.charAt(0)).toUpperCase() + fdName.substring(1);
//				md = obj.getClass().getDeclaredMethod("get"+fdName, fds[i].getType().getClasses());
//				String fdValue = md.invoke(obj).toString();
//				// fdName 再转表字段名
//				result.put(fdName, fdValue);
//			} catch (SecurityException e) {
//				e.printStackTrace();
//			} catch (NoSuchMethodException e) {
//				e.printStackTrace();
//			} catch (InvocationTargetException e) {
//				e.printStackTrace();
//			}
//			System.out.println(fds[i].getName() +"    "+ fds[i].getLong(obj));
//		}
//		return null;
//	}
	
	public JSONObject isExist(String sql, Map<Integer , Object> params){
		JSONObject user = null;
		try {
			user = querySingle(sql, params);
		} catch (SvcException e) {
			e.printStackTrace();
		}
		return user;
	}
}