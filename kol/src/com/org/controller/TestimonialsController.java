package com.org.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.org.common.CommonConstant;
import com.org.services.CommentsService;
import com.org.services.TestimonialsService;
import com.org.util.SpringUtil;
import com.org.utils.RequestUtils;
import com.org.utils.StringUtil;

@Controller
@RequestMapping("/testionials")
public class TestimonialsController {
	
	@RequestMapping("/saveContents")
	public String saveContents(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException, IOException{
		try {
			response.setHeader("Pragma","no-cache"); 
			response.setHeader("Cache-Control","no-cache"); 
			response.setDateHeader("Expires", 0); 

			HttpSession session = request.getSession(true);
			JSONObject sessionUser = (JSONObject)session.getAttribute(CommonConstant.SESSION_USER);
			
			// TODO
//			String userId = sessionUser.getString("id");
			String userId = "1";
			String contents = request.getParameter("testimonialsContent");
			contents = new String(contents.getBytes("ISO-8859-1"),"utf-8");
			
			String channelId = request.getParameter("channelId");
			String title = request.getParameter("testimonialsTitle");
			title = new String(title.getBytes("ISO-8859-1"),"utf-8");
			
			TestimonialsService tService = (TestimonialsService)SpringUtil.getBean("testimonialsService");
			tService.saveContents(userId, contents, channelId, title);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}
		return "/index.jsp";
	}
	
	private Log log = LogFactory.getLog(TestimonialsController.class);
}
