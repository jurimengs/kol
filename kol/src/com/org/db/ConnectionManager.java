package com.org.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import javax.sql.DataSource;

public class ConnectionManager {
	
	public static ConnectionManager getInstance(){
		if(cm == null){
			cm = new ConnectionManager();
		}
		return cm;
	}

	public Connection getConnection(boolean autoCommit) throws SQLException {
		Connection conn = null;
		if (dataSource == null) {
			throw new SQLException("未获取到数据源");
		}
		conn = dataSource.getConnection();
		conn.setAutoCommit(autoCommit);
		return conn;
	}

	public void closeConnection(Connection conn) throws SQLException {
		if (conn != null && !conn.isClosed()) {
			conn.close();
		}
	}

	public void closeConnection(Connection conn, Statement st)
			throws SQLException {
		if (st != null) {
			st.close();
		}

		if (conn != null && !conn.isClosed()) {
			conn.close();
		}

	}

	public void closeConnection(Connection conn, Statement... st)
			throws SQLException {

		if (st != null && st.length > 0) {
			for (Statement s : st) {
				if (s != null)
					s.close();
			}
		}

		if (conn != null && !conn.isClosed()) {
			conn.close();
		}

	}

	public void closeConnection(Connection conn, Statement st, ResultSet rs)
			throws SQLException {

		if (rs != null) {
			rs.close();
		}
		if (st != null) {
			st.close();
		}
		if (conn != null && !conn.isClosed()) {
			conn.close();
		}

	}

	public void commitConnection(Connection conn) throws SQLException {
		if (conn != null && !conn.isClosed() && !conn.getAutoCommit()) {
			conn.commit();
		}

	}

	public void rollbackConnection(Connection conn) throws SQLException {
		if (conn != null && !conn.isClosed() && !conn.getAutoCommit()) {
			conn.rollback();
		}
	}

	public Savepoint savePoint(Connection conn) throws SQLException {
		if (conn != null && !conn.isClosed()) {
			return conn.setSavepoint();
		}
		return null;
	}

	public Savepoint savePoint(Connection conn, String name)
			throws SQLException {
		if (conn != null && !conn.isClosed()) {
			return conn.setSavepoint(name);
		}
		return null;
	}

	public void rollbackToSavePoint(Connection conn, Savepoint savePoint)
			throws SQLException {
		if (conn != null && !conn.isClosed()) {
			conn.rollback(savePoint);
			String name = savePoint.getSavepointName();
		}
	}


	private static ConnectionManager cm = null;
	
	private ConnectionManager(){
		
	}
	
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
