package com.org.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

import com.org.services.busi.CommentsService;
import com.org.servlet.CommonController;
import com.org.servlet.SmpHttpServlet;
import com.org.util.SpringUtil;

@Controller
public class CommentsController extends SmpHttpServlet implements CommonController{
	private static final long serialVersionUID = 3288382584901361068L;

	public void saveComments(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		response.setHeader("Pragma","no-cache"); 
		response.setHeader("Cache-Control","no-cache"); 
		response.setDateHeader("Expires", 0); 

		//HttpSession session = request.getSession(true);
		//JSONObject sessionUser = (JSONObject)session.getAttribute(UserConstant.SESSION_USER);
		
		/* 1.获得商户端请求的值  默认设置数据处理成功 */
		String testimonialsId = request.getParameter("testimonialsId");
		String commentContent = request.getParameter("commentContent");
//		String userId = sessionUser.getString("id");
		String userId = "1";
		
		CommentsService commentsService = (CommentsService)SpringUtil.getBean("commentsService");
		commentsService.saveComments(testimonialsId, commentContent, userId);
		
		this.forward("/channel/home.do", request, response);
	}
	
	public void queryComments(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
//		HttpSession session = request.getSession(true);
//		JSONObject sessionUser = (JSONObject)session.getAttribute(UserConstant.SESSION_USER);
//			String userId = sessionUser.getString("id");
		
		String testimonialsId = request.getParameter("testimonialsId");
		
		CommentsService commentsService = (CommentsService)SpringUtil.getBean("commentsService");
		JSONObject res = commentsService.getCommentsByTesTimonialId(testimonialsId);
		request.setAttribute("res", res);

		this.forward("/comments/comments.jsp", request, response);
		return;
	}
	
	private Log log = LogFactory.getLog(CommentsController.class);

	@Override
	public void post(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
	}
}
