package com.org.controller.lulacasa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

import com.org.common.CommonConstant;
import com.org.services.busi.lulacasa.LulacasaService;
import com.org.servlet.CommonController;
import com.org.servlet.SmpHttpServlet;
import com.org.util.SpringUtil;

@Controller
public class LulacasaController extends SmpHttpServlet implements CommonController{
	private static final long serialVersionUID = 2156792239072761671L;

	public LulacasaController(){
		
	}
	
	/**
	 * resource.html 合作页面
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void saveContact(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		log.info("saveContact。。。" );
		HttpSession session = request.getSession();
		session.setAttribute(CommonConstant.CHANNEL_NAME, "首页");

		String userName = request.getParameter("userName");
		String userMobile = request.getParameter("userMobile");
		String userPhone = request.getParameter("userPhone");
		String userMail = request.getParameter("userMail");
		String userQQ = request.getParameter("userQQ");
		String userSource = request.getParameter("userSource");
		String content = request.getParameter("content");
		  
		LulacasaService service = (LulacasaService) SpringUtil.getBean("lulacasaService");
		service.saveContact(userName, userMobile, userPhone, userMail, userQQ, userSource, content);
		
		this.forward("/lulacasa/success.jsp", request, response);
		return;
	}
	
	public void saveAdvice(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		HttpSession session = request.getSession();
		session.setAttribute(CommonConstant.CHANNEL_NAME, "生活");
		session.setAttribute(CommonConstant.CURRENT_CHANNEL_ID, CommonConstant.LIFE);

		String userName = request.getParameter("userName");
		String userMobile = request.getParameter("userMobile");
		String userPhone = request.getParameter("userPhone");
		String content = request.getParameter("content");
		  
		LulacasaService service = (LulacasaService) SpringUtil.getBean("lulacasaService");
		service.saveAdvice(userName, userMobile, userPhone, content);

		this.forward("/lulacasa/success.jsp", request, response);
		return;
	}
	
	public void saveForm(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		log.info("emotion。。。" );
		
		String userName = request.getParameter("userName");
		String userMobile = request.getParameter("userMobile");
		String userPhone = request.getParameter("userPhone");
		String preTime = request.getParameter("preTime");
		String userQQ = request.getParameter("userQQ");
		String userAdress = request.getParameter("userAdress");
		String content = request.getParameter("content");
		
		// TODO 
		LulacasaService service = (LulacasaService) SpringUtil.getBean("lulacasaService");
		service.saveForm(userName, userMobile, userPhone, preTime, userQQ, userAdress, content);

		this.forward("/lulacasa/success.jsp", request, response);
		return;
	}
	
	private Log log = LogFactory.getLog(LulacasaController.class);

	@Override
	public void post(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
	}
}
