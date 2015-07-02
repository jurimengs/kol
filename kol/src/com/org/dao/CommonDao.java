package com.org.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.org.exception.SvcException;

@Repository
public class CommonDao extends BaseDao {
	public JSONObject querySingle(String sql, Map<Integer, Object> params, List<String> secretColumn)
			throws SvcException {
		JSONObject jo = null;
		try {
			JSONArray list = queryList(sql, params, secretColumn);
			if (list.size() > 1) {
				throw new SvcException(
						"Common Dao : result counts more than single");
			}
			if (list.size() <= 0) {
				return null;
			}
			return list.getJSONObject(0);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		return jo;
	}

	public <T> T querySingle(Class<T> entityClass, String sql, Map<Integer, Object> params) throws SvcException {
		T entity = null;
		try {
			entity = entityClass.newInstance();
			List<T> list = queryListByT(sql, params, entity);
			if (list.size() > 1) {
				throw new SvcException(
						"Common Dao : result counts more than single");
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

	public JSONArray queryJSONArray(String sql, Map<Integer, Object> params, List<String> secretColumn) {
		JSONArray list = null;
		try {
			list = queryList(sql, params, secretColumn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		return list;
	}

	public JSONArray queryJSONArray(String sql, List<String> secretColumn) {
		JSONArray list = null;
		try {
			list = queryList(sql, null, secretColumn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * @param entityClass
	 * @param sql
	 * @param params
	 *            ?,?,? {1:"...", 2:"...", 3:"..."}
	 * @return
	 */
	public <T> List<T> queryList(Class<T> entityClass, String sql,
			Map<Integer, Object> params) {
		T entity = null;
		try {
			entity = entityClass.newInstance();
			return queryListByT(sql, params, entity);
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

	// public void addSingle(Object obj){
	// try {
	// Entity a = obj.getClass().getAnnotation(Entity.class);
	// String tableName = a.tableName();
	// String sql = "insert into "+tableName+" values (?)";
	// // PreparedStatement ps = con.prepareStatement(sql);
	// // PreparedStatement ps = con.prepareStatement(sql, new
	// Class[]{String.class}, id);
	// // ps.execute();
	// } catch (Exception e) {
	// System.out.println(e.getMessage());
	// }
	// }

	/**
	 * 
	 * @param sql
	 * @param params
	 *            ?,?,? {1:"...", 2:"...", 3:"..."}
	 * @return
	 * @return
	 * @throws SQLException
	 * @throws SvcException
	 */
	public synchronized <T> boolean addSingle(String sql, Map<Integer, Object> params) throws SQLException {
		java.sql.Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			setStatmentParams(ps, params);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.setAutoCommit(false);
			conn.rollback();
			ps.close();
			return false;
		} finally {
			conn.close();
			ps.close();
		}
		return true;
	}

	public synchronized <T> void update(String sql, Map<Integer, Object> params)
			throws SQLException {
		java.sql.Connection conn = getConnection();

		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			setStatmentParams(ps, params);
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e1) {
			e1.printStackTrace();
			conn.setAutoCommit(false);
			conn.rollback();
			return;
		} finally {
			conn.close();
			ps.close();
		}
	}

	// 不建议使�?处理过于复杂
	// private Map<String, Object> parseObjToTableProperties(Object obj) throws
	// IllegalArgumentException, IllegalAccessException{
	// Field[] fds = obj.getClass().getDeclaredFields();
	// Method md = null;
	// Map<String, Object> result = new HashMap<String, Object>();
	// for (int i = 0; i < fds.length; i++) {
	// try {
	// String fdName = fds[i].getName();
	// fdName = String.valueOf(fdName.charAt(0)).toUpperCase() +
	// fdName.substring(1);
	// md = obj.getClass().getDeclaredMethod("get"+fdName,
	// fds[i].getType().getClasses());
	// String fdValue = md.invoke(obj).toString();
	// // fdName 再转表字段名
	// result.put(fdName, fdValue);
	// } catch (SecurityException e) {
	// e.printStackTrace();
	// } catch (NoSuchMethodException e) {
	// e.printStackTrace();
	// } catch (InvocationTargetException e) {
	// e.printStackTrace();
	// }
	// System.out.println(fds[i].getName() +"    "+ fds[i].getLong(obj));
	// }
	// return null;
	// }

	public JSONObject isExist(String sql, Map<Integer, Object> params, List<String> secretColumn) {
		JSONObject user = null;
		try {
			user = querySingle(sql, params, secretColumn);
		} catch (SvcException e) {
			e.printStackTrace();
		}
		return user;
	}
}
