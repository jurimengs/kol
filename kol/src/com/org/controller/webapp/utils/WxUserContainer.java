package com.org.controller.webapp.utils;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.org.log.LogUtil;
import com.org.log.impl.LogUtilMg;
import com.org.util.CT;
import com.org.utils.SmpPropertyUtil;
import com.org.utils.http.HttpTool;
import com.org.utils.http.impl.HttpApacheClient;

/**
 * 微信用户组信息。用于缓存用户的当前操作、用户组、用户ID等。
 * @author Administrator
 *
 */
public class WxUserContainer {
	private static JSONObject groupInfo = new JSONObject();
	private static JSONObject userList = new JSONObject();
	
	/**
	 * 组：用户：用户基本信息容器 
	 * 数据结构: 
	 * groups：
	 * 类型：json
	 * 值：     {groupid(string): userinfoArray (jsonarray)}
	 * 
	 * userinfoArray:
	 * 类型：array
	 * 值：   [userinfo(json)]
	 * 
	 * userinfo:
	 * 类型 json
	 * 值：  {nick: jurry, userid: xxxxx}
	 */
	private static JSONObject GROUP_USER_INFO = new JSONObject();
	
	/**
	 * 这种设计的假设是只有一个聊天室，后面可能会有多个的需求，实现起来相对复杂，所以暂时不实现
	 * 类型：json
	 * 值： {userid : operatetype}
	 */
	private static Map<String, Boolean> CHATING_USER_LIST = new HashMap<String, Boolean>();
	
	
	public static void joininChatingRoom(String openid){
		CHATING_USER_LIST.put(openid, true);
	}
	
	public static void exitChatingRoom(String openid){
		if(CHATING_USER_LIST.containsKey(openid)) {
			CHATING_USER_LIST.remove(openid);
			//CHATING_USER_LIST.put(openid, false);
		}
	}
	
	/**
	 * 获取聊天的人
	 * @return
	 */
	public static Map<String, Boolean> getChatingOpenidsMap(){
		return CHATING_USER_LIST;
	}
	
	/**
	 * 获取聊天的人
	 * @return
	 */
	public static JSONArray getChatingUser(){
		return JSONArray.fromObject(CHATING_USER_LIST.keySet());
	}
	
	/**
	 * 查询组id
	 * @return 当前所有的微信组
	 */
	public static synchronized JSONObject getGroupidList() {
		if(groupInfo == null || groupInfo.isEmpty()) {
			String token = Memcache.getInstance().getValue(WxUtil.WX_TOKEN);
			String remoteUrl = SmpPropertyUtil.getValue("wx", "wx_get_groupid");
			remoteUrl = remoteUrl.concat(token);
			
			JSONObject requestJson = new JSONObject();
			
			HttpTool http = new HttpApacheClient();
			groupInfo = http.httpPost(requestJson, remoteUrl, CT.ENCODE_UTF8);
			return groupInfo;
		}
		return groupInfo;
	}
	
	/**
	 * 
	 * @return
	 */
	public static synchronized JSONObject getUserList() {
		if(userList == null || userList.isEmpty()) {
			String token = Memcache.getInstance().getValue(WxUtil.WX_TOKEN);
			String remoteUrl = SmpPropertyUtil.getValue("wx", "wx_get_userid_list");
			remoteUrl = remoteUrl.concat(token);
			
			JSONObject requestJson = new JSONObject();
			
			HttpTool http = new HttpApacheClient();
			System.out.println("查询用户列表请求报文  ： " + requestJson.toString());
			userList = http.wxHttpsPost(requestJson, remoteUrl);
			return userList;
		}
		return userList;
	}
	
	/**
	 * 
	 * @openId 微信用户ID
	 * @return
	 */
	public static JSONObject getUserGroup(String openid) {
		String token = Memcache.getInstance().getValue(WxUtil.WX_TOKEN);
		String remoteUrl = SmpPropertyUtil.getValue("wx", "wx_get_user_group");
		remoteUrl = remoteUrl.concat(token);
		
		JSONObject requestJson = new JSONObject();
		requestJson.put("openid", openid);
		
		HttpTool http = new HttpApacheClient();
		LogUtil.log(WxUtil.class, "查询用户所在组请求报文  ： " + requestJson.toString(), null, LogUtilMg.LOG_INFO, CT.LOG_PATTERN_NULL);
		JSONObject userGroup = http.wxHttpsPost(requestJson, remoteUrl);
		LogUtil.log(WxUtil.class, "查询用户所在组请求报文  ： " + userGroup.toString(), null, LogUtilMg.LOG_INFO, CT.LOG_PATTERN_NULL);
		return userGroup;
	}
	
	/**
	 * 这个动作一定要在初始化微信key之后执行。否则无效
	 * 根据一些基本信息去完善用户的信息结构
	 */
	public static void initUserInfo() {
		//GROUP_USER_INFO
		// 先查询到所有的用户list
		JSONObject userList = getUserList();
		// 从返回的json数据中获取到data
		JSONObject data = userList.getJSONObject("data");
		// 从data中获取到openid数组
		JSONArray openidList = data.getJSONArray("openid");
		JSONObject userBaseInfo = null;
		// 遍历每个openid， 再查询其基本信息，缓存起来
		String openid = "";
		for (int i = 0; i < openidList.size(); i++) {
			openid = openidList.getString(i);
			// 获取用户的基本信息
			// TODO应该要在获取到信息后，保存到数据库
			userBaseInfo = getUserBaseInfo(openid);
			GROUP_USER_INFO.put(openid, userBaseInfo);
		}
	}
	
	/**
	 * 从本地缓存中获取用户基本信息，如果没有再去查询，查询后，缓存起来
	 * @param openid
	 * @return
	 */
	public static JSONObject getUserBaseInfoFromLocal(String openid) {
		//
		if(GROUP_USER_INFO.containsKey(openid)) {
			return GROUP_USER_INFO.getJSONObject(openid);
		}
		JSONObject baseInfo = getUserBaseInfo(openid);
		GROUP_USER_INFO.put(openid, baseInfo);
		return baseInfo;
	}
	
	public static JSONObject getUserBaseInfo(String openid) {
		String token = Memcache.getInstance().getValue(WxUtil.WX_TOKEN);
		String remoteUrl = SmpPropertyUtil.getValue("wx", "wx_get_user_baseinfo");
		remoteUrl = remoteUrl.concat(token).concat("&openid=").concat(openid);
		
		JSONObject requestJson = new JSONObject();
		HttpTool http = new HttpApacheClient();
		LogUtil.log(WxUtil.class, "查询用户基本信息请求报文  ： " + requestJson.toString(), null, LogUtilMg.LOG_INFO, CT.LOG_PATTERN_NULL);
		JSONObject userGroup = http.wxHttpsPost(requestJson, remoteUrl);
		LogUtil.log(WxUtil.class, "查询用户基本信息请求返回  ： " + userGroup.toString(), null, LogUtilMg.LOG_INFO, CT.LOG_PATTERN_NULL);
		return userGroup;
	}
}
