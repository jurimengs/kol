package com.org.controller.webapp;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

import com.org.controller.webapp.rute.RuteAdapter;
import com.org.controller.webapp.rute.RuteThreadPool;
import com.org.controller.webapp.utils.WxUtil;
import com.org.servlet.CommonController;
import com.org.servlet.SmpHttpServlet;
import com.org.util.CT;
import com.org.utils.StringUtil;
import com.org.utils.XmlUtils;

@Controller
public class WxController extends SmpHttpServlet implements CommonController{
	private static final long serialVersionUID = 2156792239072761671L;
	private Log log = LogFactory.getLog(WxController.class);

	public WxController(){
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void validate(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("token: " + this.getParamMap(request));
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		
		boolean signResult = WxUtil.checkSignature(signature, timestamp, nonce);
		if(!signResult) {
			log.info("验签错误");
			return;
		}
		String echostr = request.getParameter("echostr");
		if(StringUtils.isNotEmpty(echostr)) {
			// 表示是首次验签
			this.write(echostr, CT.ENCODE_UTF8, response);
			return;
		}
		
		JSONObject xmlJson = XmlUtils.getDocumentFromRequest(request);
		log.info("收到微信服务器的消息：xmlJson=====> " + xmlJson);
		Callable<String> event = RuteAdapter.adapter(xmlJson);
		Future<String> result = RuteThreadPool.submit(event);
		//Future<String> result = DealThread.dealCallable(event);
		
		this.write(result.get(), CT.ENCODE_UTF8, response);
		return;
	}
	
	// TODO 测试用的
	public void getCacheToken(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String token = WxUtil.getToken();
		String timestamp = String.valueOf(StringUtil.getTimestamp()); // 必填，生成签名的时间戳
		String nonceStr = UUID.randomUUID().toString(); // 必填，签名，见附录1
		String url = request.getRequestURL().toString();
		String signature = WxUtil.localSign(timestamp, nonceStr, url); // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		
		request.setAttribute("timestamp", timestamp);
		request.setAttribute("nonceStr", nonceStr);
		request.setAttribute("url", url);
		request.setAttribute("signature", signature);
		request.setAttribute("cacheToken", token);
		this.forward("/www/html/wxtest.jsp", request, response);
		return;
	}
	
	public void initBottomMenu(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WxUtil.createBottomMenu();
		//this.forward("/www/html/wxtest.jsp", request, response);
		return;
	}
	
	public void deleteBottomMenu(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WxUtil.deleteBottomMenu();
		//this.forward("/www/html/wxtest.jsp", request, response);
		return;
	}
	
	public void post(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
	}
	
}
