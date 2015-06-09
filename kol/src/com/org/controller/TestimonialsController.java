package com.org.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

import com.org.services.busi.TestimonialsService;
import com.org.servlet.CommonController;
import com.org.servlet.SmpHttpServlet;
import com.org.util.SpringUtil;

@Controller
public class TestimonialsController extends SmpHttpServlet implements CommonController{
	private static final long serialVersionUID = -2554067244094241952L;

	public void saveContents(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException, IOException{

		//JSONObject sessionUser = (JSONObject)session.getAttribute(UserConstant.SESSION_USER);
//			String userId = sessionUser.getString("id");
		
		// TODO
		String userId = "1";
		String contents = request.getParameter("testimonialsContent");
		
		String channelId = request.getParameter("channelId");
		String title = request.getParameter("testimonialsTitle");
		
		TestimonialsService tService = (TestimonialsService)SpringUtil.getBean("testimonialsService");
		
		JSONObject res = tService.saveContents(userId, contents, channelId, title);
		// TODO 判断结果是否成功. 如果成功，跳转首页
		response.sendRedirect("/channel/home.do");
		return;
	}
	
	private Log log = LogFactory.getLog(TestimonialsController.class);

	@Override
	public void post(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
