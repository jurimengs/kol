package com.org.controller.webapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;

import com.org.controller.webapp.utils.WxUserContainer;
import com.org.servlet.CommonController;
import com.org.servlet.SmpHttpServlet;

@Controller
public class WxUserController extends SmpHttpServlet implements CommonController{
	private static final long serialVersionUID = 2156792239072761671L;

	public WxUserController(){
		
	}
	
	public void getGroupidList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject responseJson = WxUserContainer.getGroupidList();
		request.setAttribute("groupid", responseJson.toString());
		System.out.println("getGroupid"+ responseJson);
		this.forward("/www/html/test_grouplist.jsp", request, response);
		return;
	}
	
	public void getUserList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		JSONObject userlist = WxUserContainer.getUserList();
//		JSONObject data = userlist.getJSONObject("data");
//		String openidList = data.getString("openid");
//		for (int i = 0; i < openidList.size(); i++) {
//			System.out.println(openidList.getString(i));
//		}
//		openidList = openidList.replace("\"", "").replace("[", "").replace("]", "");
//		request.setAttribute("openidList", openidList);
//		this.forward("/www/html/test_userlist.jsp", request, response);
//		return;
	}
	
	public void getUserGroup(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String openid = request.getParameter("openid");
		JSONObject group = WxUserContainer.getUserGroup(openid);
		request.setAttribute("group", group.toString());
		this.forward("/www/html/test_usergroup.jsp", request, response);
		return;
	}
	
	public void getUserBaseInfo(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String openid = request.getParameter("openid");
		JSONObject baseinfo = WxUserContainer.getUserBaseInfo(openid);
		request.setAttribute("baseinfo", baseinfo);
		this.forward("/www/html/test_userbaseinfo.jsp", request, response);
		return;
	}
	
	public void post(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
	}
	
}
