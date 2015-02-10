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
import com.org.services.DataSourceContainer;
import com.org.utils.JSONUtils;
import com.org.utils.RequestUtils;
import com.org.utils.SmpPropertyUtil;
import com.org.utils.StringUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	private final static DataSourceContainer dsc = DataSourceContainer.getInstance();
	
	private Log log = LogFactory.getLog(UserController.class);
	@RequestMapping("/toregist")
	public String toregist(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException, IOException{
		// 一、加载数据源
		/* 0. 设置数据部缓存  */
		response.setHeader("Pragma","no-cache"); 
		response.setHeader("Cache-Control","no-cache"); 
		response.setDateHeader("Expires", 0); 
		return "/user/regist.jsp";
	}
	
	@RequestMapping("/regist")
	public String regist(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException, IOException{
		// 一、加载数据源
		/* 0. 设置数据部缓存  */
		try {
			response.setHeader("Pragma","no-cache"); 
			response.setHeader("Cache-Control","no-cache"); 
			response.setDateHeader("Expires", 0); 
			/* 1.获得商户端请求的值  默认设置数据处理成功 */
			String loginName = request.getParameter("");
			Map<String,String> paramMap = RequestUtils.getParamMap(request);
			log.info("收到请求参数：　" + StringUtil.mapStringToString(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
			return "error.jsp";
		}
		
		return "/user/usercenter.jsp";
	}

}
