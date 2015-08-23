package com.org.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.org.common.CommonConstant;
import com.org.common.UserConstant;
import com.org.exception.SvcException;
import com.org.services.busi.UserService;
import com.org.servlet.CommonController;
import com.org.servlet.SmpHttpServlet;
import com.org.util.MD5;
import com.org.util.SpringUtil;

@Controller
public class UserController extends SmpHttpServlet implements CommonController{
	private static final long serialVersionUID = -6419478230418815050L;

	public void loginOut(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		JSONObject sessionUser = (JSONObject)request.getSession(true).getAttribute(UserConstant.SESSION_USER);
		if(sessionUser != null && !sessionUser.isEmpty()) {
			request.getSession(true).removeAttribute(UserConstant.SESSION_USER);
		}
		this.forward("/user/toLogin.do", request, response);
		return;
	}
	
	/**
	 * TODO 应该做成ajax
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void login(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		HttpSession session = request.getSession(true);
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		
		UserService uService = (UserService)SpringUtil.getBean("userService");
		JSONObject user = uService.getUserByLoginName(loginName);
		if(user != null && !user.isEmpty()) {
			if(user.getString(UserConstant.PWD).equals(MD5.getMD5(password))) {
				session.setAttribute(UserConstant.SESSION_USER, user);
			} else {

				request.setAttribute("respCode", "SEC001");
				request.setAttribute("respMsg", "密码错误");
					
				this.forward("/error.jsp", request, response);
				return;
			}
		} else {
			request.setAttribute("respCode", "USER001");
			request.setAttribute("respMsg", "该用户不存在, <a href='/user/toRegist.do'>请去这里注册</a>, 或者<a href='javascript:void(0);' onclick='history.back();'>返回</a>");
				
			this.forward("/error.jsp", request, response);
			return;
		}
		
		this.forward("/channel/home.do", request, response);
		return;
	}

	public void toLogin(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		log.info("toRegist...");
//		String sessionId = request.getSession().getId();
//		System.err.println(sessionId);
//		String tempSecurityKey = SecurityUtil.createPersionalKey(sessionId);
		
		this.forward("/login.jsp", request, response);
		return;
	}
	
	public void regist(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		String loginName = request.getParameter("loginName");
		String email = request.getParameter("email");
		String mobile = "";
		String registType = CommonConstant.REGIST_TYPE_PERSON;
		String nickName = "";
		String password = request.getParameter("password");
		password = MD5.getMD5(password);
		
		UserService uService = (UserService)SpringUtil.getBean("userService");
		JSONObject user = uService.getUserByLoginName(loginName);
		if(user == null || user.isEmpty()) {
			uService.saveUser(loginName, email, mobile, registType, nickName, password);
			// 再查一遍
			user = uService.getUserByLoginName(loginName);
			request.getSession(true).setAttribute(UserConstant.SESSION_USER, user);
		} else {
			throw new SvcException("该用户已经被注册,请尝试更换用户名");
		}
		
		this.forward("/channel/home.do", request, response);
		return;
	}
	
	public void toRegist(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		log.info("toRegist...");
//		String sessionId = request.getSession().getId();
//		System.err.println(sessionId);
//		String tempSecurityKey = SecurityUtil.createPersionalKey(sessionId);
		
		this.forward("/regist.jsp", request, response);
		return;
	}
	
	private Log log = LogFactory.getLog(UserController.class);

	@Override
	public void post(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
