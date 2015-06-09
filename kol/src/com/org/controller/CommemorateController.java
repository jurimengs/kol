package com.org.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

import com.org.services.busi.CommentsService;
import com.org.servlet.CommonController;
import com.org.servlet.SmpHttpServlet;
import com.org.util.SpringUtil;

@Controller
public class CommemorateController extends SmpHttpServlet implements CommonController{
	private static final long serialVersionUID = -3498132823103396194L;

	public void queryComments(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		HttpSession session = request.getSession(true);
		//JSONObject sessionUser = (JSONObject)session.getAttribute(UserConstant.SESSION_USER);
//			String userId = sessionUser.getString("id");
		
		String testimonialsId = request.getParameter("testimonialsId");
		
		CommentsService commentsService = (CommentsService)SpringUtil.getBean("commentsService");
		JSONObject res = commentsService.getCommentsByTesTimonialId(testimonialsId);
		session.setAttribute("res", res);
		this.redirect("/comments/comments.jsp", response);
		return;
	}
	
	private Log log = LogFactory.getLog(CommemorateController.class);

	@Override
	public void post(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
