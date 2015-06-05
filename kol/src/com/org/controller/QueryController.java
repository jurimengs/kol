package com.org.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.org.Connection;
import com.org.common.CommonConstant;
import com.org.utils.JSONUtils;
import com.org.utils.RequestUtils;
import com.org.utils.StringUtil;

@Controller
@RequestMapping("/query")
public class QueryController {
	
	private Log log = LogFactory.getLog(QueryController.class);
	@RequestMapping("/executeQuery")
	public void start(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException, IOException{
		// 一、加载数据源
		/* 0. 设置数据部缓存  */
		response.setHeader("Pragma","no-cache"); 
		response.setHeader("Cache-Control","no-cache"); 
		response.setDateHeader("Expires", 0); 
		/* 1.获得商户端请求的值  默认设置数据处理成功 */
		Map<String,String> paramMap = RequestUtils.getParamMap(request);
		log.info("收到远程请求参数：　" + StringUtil.mapStringToString(paramMap));
		
		JSONObject requestJson = JSONUtils.getJsonFromStrStrMap(paramMap);
		JSONObject result = RequestUtils.precheckParmas(requestJson);
		if(! result.getString(CommonConstant.RESP_CODE).equals("10000")){
			response.getOutputStream().write(result.toString().getBytes("UTF-8"));
			log.info(result.getString(CommonConstant.RESP_CODE) +": " + result.getString(CommonConstant.RESP_MSG));
			return ;
		}
		
		String identityFlag = requestJson.getString("identityFlag");
		// 根据身份，路由到指定的数据库
		Connection con = null;
		if(con == null){
			// 尝试从请求中获取数据库参数，动态加载数据源，成功后返回con
			
		}
		
		// 二、执行查询
		result = con.executeQuery(requestJson);
		
		// 三、返回数据
		log.info("远程请求返回数据：　" + result.toString());
		response.getOutputStream().write(result.toString().getBytes("UTF-8"));
		return;
	}

}
