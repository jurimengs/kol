package com.org.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BaseDao{
	public void queryUserInfo(String sql, Map<Integer, Object> params){
		try {
			System.out.println("====================queryUserInfo" );
			
			PreparedStatement ps = getConnection().prepareStatement(sql);
			for (Iterator<Integer> iterator = params.keySet().iterator(); iterator.hasNext();) {
				Integer key = iterator.next();
				ps.setObject(key, params.get(key));
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				System.out.println("====================" + rs.getInt(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
