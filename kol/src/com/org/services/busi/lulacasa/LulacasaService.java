package com.org.services.busi.lulacasa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.org.dao.CommonDao;
import com.org.util.SpringUtil;
import com.org.utils.DateUtil;

/**
 * Õ®”√
 * @author Administrator
 *
 */
@Service
public class LulacasaService {
	private final static List<String> secretColumn = new ArrayList<String>();
	
	static {
		secretColumn.add("id");
	}
	
	public JSONObject saveContact(String userName, String userMobile,
			String userPhone, String userMail, String userQQ, String userSource,
			String content) {
		// t_contact
		CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
		Map<Integer , Object> params = new HashMap<Integer, Object>();

		String createTime = DateUtil.getDateStringByFormat(DateUtil.yyyyMMddHHmmss);
		
		params.put(1, userName);
		params.put(2, userMobile);
		params.put(3, userPhone);
		params.put(4, userMail);
		params.put(5, userQQ);
		params.put(6, userSource);
		params.put(7, content);
		params.put(8, createTime);
		  
		String sql = "insert into t_contact (user_name, user_mobile, user_phone, user_mail, user_qq, user_source, content, create_time) values (?, ?, ?, ?, ?, ?, ?, ?) ";
		try {
			commonDao.addSingle(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return null;
	}
	
	public JSONArray saveAdvice(String userName, String userMobile,
			String userPhone, String content) {
		// t_advice
		
		CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
		Map<Integer , Object> params = new HashMap<Integer, Object>();
		
		String createTime = DateUtil.getDateStringByFormat(DateUtil.yyyyMMddHHmmss);
		
		params.put(1, userName);
		params.put(2, userMobile);
		params.put(3, userPhone);
		params.put(4, content);
		params.put(5, createTime);
		String sql = "insert into t_advice (user_name, user_mobile, user_phone, content, create_time) values (?, ?, ?, ?, ?)";
		try {
			commonDao.addSingle(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return null;
	}
	
	public JSONArray saveForm(String userName, String userMobile,
			String userPhone, String preTime, String userQQ, String userAdress,
			String content) {
		// t_form
		CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
		Map<Integer , Object> params = new HashMap<Integer, Object>();
		String createTime = DateUtil.getDateStringByFormat(DateUtil.yyyyMMddHHmmss);
		
		params.put(1, userName);
		params.put(2, userMobile);
		params.put(3, userPhone);
		params.put(4, preTime);
		params.put(5, userQQ);
		params.put(6, userAdress);
		params.put(7, content);
		params.put(8, createTime);
		String sql = "insert into t_form (user_name, user_mobile, user_phone, pre_time, user_qq, user_address, content, create_time) values (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			commonDao.addSingle(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return null;
	}
}
