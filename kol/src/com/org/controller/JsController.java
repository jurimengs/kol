package com.org.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

import com.org.common.CommonConstant;
import com.org.common.UserConstant;
import com.org.container.CommonContainer;
import com.org.services.busi.ChannelService;
import com.org.services.busi.CommemorateService;
import com.org.services.busi.JsService;
import com.org.servlet.CommonController;
import com.org.servlet.SmpHttpServlet;
import com.org.util.SpringUtil;
import com.org.utils.DateUtil;
import com.org.utils.SmpPropertyUtil;

/**
 * ÓÃ»§²Ù×÷¸ú×Ù
 * @author Administrator
 *
 */
@Controller
public class JsController extends SmpHttpServlet implements CommonController{
	private static final long serialVersionUID = 2156792239072761671L;
	public JsController(){
		
	}
	
	@Override
	public void post(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	}
	
	public void jsListener(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userId = "";
		if(request.getSession(true).getAttribute(UserConstant.SESSION_USER) != null) {
			JSONObject user = (JSONObject) request.getSession(true).getAttribute(UserConstant.SESSION_USER);
			userId = user.getString("id");
		}
		
		String browserName = request.getParameter("browserName");
		String browserVersion = request.getParameter("browserVersion");
		String operateName = request.getParameter("operate");
		String operateDateTime = DateUtil.getDateStringByFormat(DateUtil.yyyyMMddHHmmss);
		String currentPage = request.getParameter("currentPage");
		String userAgent = request.getParameter("userAgent");
		String localAddr = request.getLocalAddr();
		String remoteAddr =  request.getRemoteAddr();
		
		JsService js = (JsService) SpringUtil.getBean("jsService");
		js.save(userId, browserName, browserVersion, operateName, operateDateTime, 
				currentPage, userAgent, localAddr, remoteAddr);
		
		log.info("jsListener..."+browserName);
	}
	
	private Log log = LogFactory.getLog(JsController.class);
}
