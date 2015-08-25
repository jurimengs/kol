package com.org.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import com.org.servlet.CommonController;
import com.org.servlet.SmpHttpServlet;
import com.org.utils.DateUtil;

/**
 * HTML5 ÏûÏ¢ÍÆËÍ
 * @author Administrator
 *
 */
@Controller
public class MessagePushController extends SmpHttpServlet implements CommonController{
	private static final long serialVersionUID = -2554067244094241952L;

	public void push(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		response.setHeader("Content-Type", "text/event-stream");
		response.setHeader("Cache-Control", "no-cache");
		
		String date = DateUtil.getDateStringByFormat(DateUtil.DATE_FORMAT_DATE);
		response.getOutputStream().println(date);
		response.getOutputStream().println();
		response.getOutputStream().flush();
		return;
	}

	@Override
	public void post(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
	}
	
}
