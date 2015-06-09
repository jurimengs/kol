package com.org.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.org.servlet.CommonController;
import com.org.servlet.SmpHttpServlet;
import com.org.utils.RequestUtils;
import com.org.utils.StringUtil;

@Controller
public class UserController extends SmpHttpServlet implements CommonController{
	private static final long serialVersionUID = -6419478230418815050L;

	public void toregist(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		response.setHeader("Pragma","no-cache"); 
		response.setHeader("Cache-Control","no-cache"); 
		response.setDateHeader("Expires", 0);
		this.forward("/user/regist.jsp", request, response);
		return;
	}
	
	public void regist(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		//String loginName = request.getParameter("");
		Map<String,String> paramMap = RequestUtils.getParamMap(request);
		log.info("收到请求参数：　" + StringUtil.mapStringToString(paramMap));

		this.forward("/user/usercenter.jsp", request, response);
		return;
	}
	
	private Log log = LogFactory.getLog(UserController.class);

	@Override
	public void post(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
