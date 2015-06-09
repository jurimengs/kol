package com.org.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.org.services.busi.TestimonialsService;
import com.org.util.SpringUtil;

@Controller
@RequestMapping("/testionials")
public class TestimonialsController {
	
	@RequestMapping("/saveContents")
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
}
