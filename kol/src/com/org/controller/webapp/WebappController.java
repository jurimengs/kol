package com.org.controller.webapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;

import com.org.controller.webapp.utils.WxUserContainer;
import com.org.servlet.CommonController;
import com.org.servlet.SmpHttpServlet;

@Controller
public class WebappController extends SmpHttpServlet implements CommonController{
	private static final long serialVersionUID = 2156792239072761671L;

	public WebappController(){
		
	}

	public void validate(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.forward("/wx/validate.do", request, response);
	}

	public void post(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.forward("/wx/validate.do", request, response);
	}
	
}
