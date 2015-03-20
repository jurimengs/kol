package com.org.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.org.common.UserConstant;
import com.org.services.busi.CommentsService;
import com.org.util.SpringUtil;

@Controller
@RequestMapping("/comments")
public class CommentsController {
	
	@RequestMapping("/saveComments")
	public String regist(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException, IOException{
		try {
			response.setHeader("Pragma","no-cache"); 
			response.setHeader("Cache-Control","no-cache"); 
			response.setDateHeader("Expires", 0); 

			HttpSession session = request.getSession(true);
			JSONObject sessionUser = (JSONObject)session.getAttribute(UserConstant.SESSION_USER);
			
			/* 1.获得商户端请求的值  默认设置数据处理成功 */
			String testimonialsId = request.getParameter("testimonialsId");
			String commentContent = request.getParameter("commentContent");
//			String userId = sessionUser.getString("id");
			String userId = "1";
			
			CommentsService commentsService = (CommentsService)SpringUtil.getBean("commentsService");
			commentsService.saveComments(testimonialsId, commentContent, userId);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}
		return "/index.jsp";
	}
	
	@RequestMapping("/queryComments")
	public String queryComments(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException, IOException{
		try {
			response.setHeader("Pragma","no-cache"); 
			response.setHeader("Cache-Control","no-cache"); 
			response.setDateHeader("Expires", 0); 
			
			HttpSession session = request.getSession(true);
			JSONObject sessionUser = (JSONObject)session.getAttribute(UserConstant.SESSION_USER);
			
			/* 1.获得商户端请求的值  默认设置数据处理成功 */
			String testimonialsId = request.getParameter("testimonialsId");
//			String userId = sessionUser.getString("id");
			String userId = "1";
			
			CommentsService commentsService = (CommentsService)SpringUtil.getBean("commentsService");
			JSONObject res = commentsService.getCommentsByTesTimonialId(testimonialsId);
			session.setAttribute("res", res);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}
		return "/commonts/comments.jsp";
	}
	
	private Log log = LogFactory.getLog(CommentsController.class);
}
