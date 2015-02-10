package com.org.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.util.StringUtils;

import com.org.Connection;
import com.org.common.CommonConstant;

public class RequestUtils {
	public static JSONObject precheckParmas(JSONObject requestJson) {
		
		JSONObject result = new JSONObject();
		String identityFlag = null;
		if(!requestJson.containsKey("identityFlag") || StringUtils.isEmpty(requestJson.getString("identityFlag"))){
			result.put(CommonConstant.RESP_CODE, "10001");
			result.put(CommonConstant.RESP_MSG, "identityFlag不能为空");
			return result;
		}
		identityFlag = requestJson.getString("identityFlag");
			
		if(identityFlag.equals("monitor")){
			if(!requestJson.containsKey("queryFlag") || StringUtils.isEmpty(requestJson.getString("queryFlag"))){
				result.put(CommonConstant.RESP_CODE, "10001");
				result.put(CommonConstant.RESP_MSG, "queryFlag不能为空");
				return result;
			}
		}
		
		if(!requestJson.containsKey("queryParams") || requestJson.getJSONObject("queryParams").isNullObject()){
			result.put(CommonConstant.RESP_CODE, "10001");
			result.put(CommonConstant.RESP_MSG, "queryParams不能为空");
			return result;
		}
		
		if(identityFlag.equals("monitor")){
			if(!requestJson.containsKey("collectionName") || StringUtils.isEmpty(requestJson.getString("collectionName"))){
				result.put(CommonConstant.RESP_CODE, "10001");
				result.put(CommonConstant.RESP_MSG, "collectionName不能为空");
				return result;
			}
		}
		
		result.put(CommonConstant.RESP_CODE, "10000");
		result.put(CommonConstant.RESP_MSG, "预检成功");
		return result;
		
	}

	public static Map<String, String> getParamMap(HttpServletRequest request) {
		Map<String,String> paramMap = new HashMap<String, String>();
		Enumeration<String> enumeration = request.getParameterNames();
		while(enumeration.hasMoreElements()){
			String name = enumeration.nextElement();
            paramMap.put(name, request.getParameter(name));
		}
		return paramMap;
	
	}
}
