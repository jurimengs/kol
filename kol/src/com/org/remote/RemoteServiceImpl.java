package com.org.remote;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.org.Connection;
import com.org.utils.JSONUtils;

// TODO 
public class RemoteServiceImpl implements RemoteService {
	//private final static DataSourceContainer dsc = DataSourceContainer.getInstance();
	private Log log = LogFactory.getLog(RemoteServiceImpl.class);

	@Override
	public String access(String request) {
		// 一、加载数据源

		JSONObject requestJson = JSONUtils.getJsonFromString(request);
		
		String identityFlag = requestJson.getString("identityFlag");
		// 根据身份，路由到指定的数据库
		Connection con = null;
//		if(identityFlag.equals("monitor")){
//			con = dsc.getConnection(CommonConstant.DB_MONGO);
//		} else {
//			con = dsc.getConnection(CommonConstant.DB_HIKARICP);
//		}
		
		// 二、执行查询
		JSONObject result = executeQuery(requestJson, con);
		
		// 三、返回数据
		return result.toString();
	}

	private JSONObject executeQuery(JSONObject requestJson, Connection con) {
		// TODO 
		return null;
	}

}
