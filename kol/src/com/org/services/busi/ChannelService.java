package com.org.services.busi;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.org.dao.CommonDao;
import com.org.util.MD5;
import com.org.util.SpringUtil;

/**
 * 通用
 * @author Administrator
 *
 */
@Service
public class ChannelService {
	public JSONObject checkLogin(String loginName, String LoginPwd){
		// 先查session有没有用户　
		JSONObject result = new JSONObject();
		if(StringUtils.isEmpty(loginName) || StringUtils.isEmpty(LoginPwd)){
			result.put("respCode", "10001");
			result.put("respMsg", "用户名　密码不能为空");
			System.out.println("用户名　密码不能为空");
			return result;
		}
		
		JSONObject fullUserInfo = searchFullUserInfo(loginName);
		if(fullUserInfo == null || fullUserInfo.isNullObject()){
			result.put("respCode", "10002");
			result.put("respMsg", "用户不存在");
			System.out.println("用户不存在");
			return result;
		}
		if(!MD5.getMD5(LoginPwd).equals(fullUserInfo.getString("password"))){
			result.put("respCode", "10003");
			result.put("respMsg", "密码错误");
			return result;
		}
		result.put("respCode", "10000");
		result.put("respMsg", "登录成功");
		result.put("fullUserInfo", fullUserInfo);
		return result;
	}
	
	/**
	 * 企业用户登录校验
	 * @param loginName
	 * @param LoginPwd
	 * @return
	 */
	public JSONObject checkLoginComp(String loginName, String LoginPwd){
		// 先查session有没有用户　
		JSONObject result = new JSONObject();
		if(StringUtils.isEmpty(loginName) || StringUtils.isEmpty(LoginPwd)){
			result.put("respCode", "10001");
			result.put("respMsg", "用户名　密码不能为空");
			System.out.println("用户名　密码不能为空");
			return result;
		}
		
		JSONObject fullUserInfo = searchFullUserInfoComp(loginName);
		if(fullUserInfo == null || fullUserInfo.isNullObject()){
			result.put("respCode", "10002");
			result.put("respMsg", "用户不存在");
			System.out.println("用户不存在");
			return result;
		}
		if(!MD5.getMD5(LoginPwd).equals(fullUserInfo.getString("password"))){
			result.put("respCode", "10003");
			result.put("respMsg", "密码错误");
			return result;
		}
		result.put("respCode", "10000");
		result.put("respMsg", "登录成功");
		result.put("fullUserInfo", fullUserInfo);
		return result;
	}
	
	public JSONObject searchFullUserInfo(String loginName){
		String sql = "select * from plat_user_extends_info e right join plat_user_info u on e.login_name = u.login_name where u.login_name = ?"; 
		CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
		//String sql = "select * from plat_user_extends_info where login_name = ?";
		Map<Integer , Object> params = new HashMap<Integer, Object>();
		params.put(1, loginName);
		//params.put(2, MD5.getMD5(LoginPwd));
		JSONObject fullUserInfo = commonDao.isExist(sql, params);
		return fullUserInfo;
	}

	public JSONObject searchFullUserInfoComp(String loginName){
		String sql = "select * from plat_company_extends_info e right join plat_company_info u on e.login_name = u.login_name where u.login_name = ?"; 
		CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
		//String sql = "select * from plat_user_extends_info where login_name = ?";
		Map<Integer , Object> params = new HashMap<Integer, Object>();
		params.put(1, loginName);
		//params.put(2, MD5.getMD5(LoginPwd));
		JSONObject fullUserInfo = commonDao.isExist(sql, params);
		return fullUserInfo;
	}

	
	/**
	 * @param loginName
	 * @return
	 */
	public JSONArray getTestimonialsByChannelId(String channelId){
		String sql = "select * from kol_testimonials where channel_id = ? order by id desc"; 
		CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
		Map<Integer , Object> params = new HashMap<Integer, Object>();
		params.put(1, channelId);
		JSONArray testimonials = commonDao.queryJSONArray(sql, params);
		return testimonials;
	}
	
	public JSONArray getTestimonialsByChannelId(String channelId, String limit){
		String sql = "select * from kol_testimonials where channel_id = ? order by id desc limit ?";
		CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
		Map<Integer , Object> params = new HashMap<Integer, Object>();
		params.put(1, channelId);
		params.put(2, Integer.valueOf(limit));
		if(StringUtils.isEmpty(channelId)){
			params = new HashMap<Integer, Object>();
			sql = "select * from kol_testimonials order by id desc limit ?";
			params.put(1, Integer.valueOf(limit));
		}
		JSONArray testimonials = commonDao.queryJSONArray(sql, params);
		return testimonials;
	}
	
	public JSONArray getTestimonialsByChannelId(String channelId, String limitFrom, String limitTo){
		String sql = "select * from kol_testimonials where channel_id = ? order by id desc"; 
		CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
		Map<Integer , Object> params = new HashMap<Integer, Object>();
		params.put(1, channelId);
		JSONArray testimonials = commonDao.queryJSONArray(sql, params);
		return testimonials;
	}
}
