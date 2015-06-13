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

/**
 * ¼ÍÄî°å
 * @author Administrator
 *
 */
@Controller
public class CommemorateController extends SmpHttpServlet implements CommonController{
	private static final long serialVersionUID = -3498132823103396194L;

	public void queryComments(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		
		String testimonialsId = request.getParameter("testimonialsId");
		
		CommentsService commentsService = (CommentsService)SpringUtil.getBean("commentsService");
		JSONObject res = commentsService.getCommentsByTesTimonialId(testimonialsId);
		request.setAttribute("res", res);
		
		this.forward("/comments/comments.jsp", request, response);
		return;
	}
	

	public void toAddPage(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		//JSONObject sessionUser = (JSONObject)session.getAttribute(UserConstant.SESSION_USER);
//			String userId = sessionUser.getString("id");
		this.forward("/channel/addCommemorate.jsp", request, response);
		return;
	}
	
	private Log log = LogFactory.getLog(CommemorateController.class);

	@Override
	public void post(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
