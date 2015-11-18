package com.org.controller.webapp;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

import com.org.controller.webapp.utils.Memcache;
import com.org.controller.webapp.utils.WxUserContainer;
import com.org.controller.webapp.utils.WxUtil;
import com.org.servlet.CommonController;
import com.org.servlet.SmpHttpServlet;
import com.org.util.CT;
import com.org.utils.SmpPropertyUtil;
import com.org.utils.StringUtil;
import com.org.utils.XmlUtils;
import com.org.utils.http.HttpTool;
import com.org.utils.http.impl.HttpApacheClient;

@Controller
public class WxController extends SmpHttpServlet implements CommonController{
	private static final long serialVersionUID = 2156792239072761671L;

	public WxController(){
		
	}
	
	public void toWxTest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("token" + this.getParamMap(request));
		
		this.forward("/www/html/wxtest.jsp", request, response);
		return;
	}
	
	private Log log = LogFactory.getLog(WxController.class);

	/**
	 * 第一次微信验证的时候需要用这个模块. 这是一个模板方法.
	 * @param request
	 * @param response
	 * @throws Exception
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
		
		this.write(echostr, CT.ENCODE_UTF8, response);
		return;
	}
	*/
	
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
		
		JSONObject xmlJson = XmlUtils.getDocumentFromRequest(request);
		log.info("收到微信服务器的消息：xmlJson=====> " + xmlJson);
		// TODO 微信服务器推送的所有操作都会往这里推送。 所以， 要定制一个路由规则
		// xmlJson 有一个字段叫 MsgType ， 它的类型有很多种： event 表示是事件/ text 表示是文本消息 等
		String returnStr = "";
		if(xmlJson.getString("MsgType").equals("event")) {
			returnStr = dealEvent(xmlJson);
		} else if(xmlJson.getString("MsgType").equals("text")) {
			// 发消息的人
			String msgFromOpenid = xmlJson.getString("FromUserName");
			// 发消息者的昵称
			String nick = WxUserContainer.getUserBaseInfo(msgFromOpenid).getString("nickname") ;
			Map<String, Boolean> chatingUsersMap = WxUserContainer.getChatingOpenidsMap();
			// 判断下是否在聊天室
			if(chatingUsersMap.containsKey(msgFromOpenid) && chatingUsersMap.get(msgFromOpenid)) {
				// 直接回复success， 由客服接口去发送消息
				returnStr = "success";
				// 这是获取所有的用户
				// JSONObject userListJson = WxUserContainer.getUserList();
				// JSONObject data = userListJson.getJSONObject("data");
				// JSONArray openidList = data.getJSONArray("openid");
				JSONArray chatingUserArray = WxUserContainer.getChatingUser();
				// 从组中除去发信息者自己
				chatingUserArray.remove(msgFromOpenid);
				log.info("openidList ====>"+chatingUserArray);
				
				String content = nick + "说:\n"+xmlJson.getString("Content");
				// 从0开始递归发送，实现群发
				pushMassMessage(msgFromOpenid, chatingUserArray, content, 0);
			} else {
				returnStr = WxUtil.createMenu(xmlJson);
			}
		}
		
		System.out.println("响应的: " + returnStr);
		this.write(returnStr, CT.ENCODE_UTF8, response);
		return;
	}
	
	private String dealEvent(JSONObject xmlJson) {
		String FromUserName = xmlJson.getString("FromUserName");
		String Event = xmlJson.getString("Event");
		String EventKey = xmlJson.getString("EventKey"); // 对应自定义的key 值
		log.info("EventKey ====>" + EventKey);
		// 拿事件类型 和 点击的按钮key值判断 可以决定业务类型
		if(Event.equals("CLICK")) {
			if(WxUtil.ENTER_CHATING_ROOM.equals(EventKey)) {
				// 加入聊天室
				WxUserContainer.joininChatingRoom(FromUserName);
				pushMessage(FromUserName, "您已进入聊天室, 可以和大家聊天啦");
			} else if(WxUtil.EXIT_CHATING_ROOM.equals(EventKey)) {
				// 加入聊天室
				WxUserContainer.exitChatingRoom(FromUserName);
				pushMessage(FromUserName, "您已退出聊天室");
			}
		}
		return "";
	}
	
	/**
	 * 递归发送消息给用户列表中的用户
	 * @param openidList
	 * @param content
	 * @param nextOpenid
	 * @return
	 */
	public void pushMassMessage(String msgFromOpenid, JSONArray openidList, String content, int nextOpenid){
		if(nextOpenid >= openidList.size()) {
			log.info("递归完成");
			return ;
		}
		String toOpenid = openidList.getString(nextOpenid);
		
		// 调用客服接口发送消息 
		String url = SmpPropertyUtil.getValue("wx", "wx_send_message_by_service");
		url = url.concat(Memcache.getInstance().getValue(WxUtil.WX_TOKEN));
		
		JSONObject contentTemp = new JSONObject();
		contentTemp.put("content", content);
		// 加入聊天室
		WxUserContainer.joininChatingRoom(toOpenid);
		
		JSONObject paramContent = new JSONObject();
		paramContent.put("touser", toOpenid);
		paramContent.put("msgtype", "text");
		paramContent.put("text", contentTemp);
		
		HttpTool http = new HttpApacheClient();
		JSONObject pushResult = http.wxHttpsPost(paramContent, url);
		if(pushResult.getString("errcode").equals("0")) {
			// 推送成功
			log.info("客服接口消息发送成功，用户id：" + toOpenid);
		}
		// 下一个openid的索引
		nextOpenid ++;
		// 递归发送
		pushMassMessage(msgFromOpenid, openidList, content, nextOpenid);

	}
	
	/**
	 * 发送消息给指定用户
	 * @param toOpenid
	 * @param content
	 * @return
	 */
	public JSONObject pushMessage(String toOpenid, String content){

		// 调用客服接口发送消息 
		String url = SmpPropertyUtil.getValue("wx", "wx_send_message_by_service");
		url = url.concat(Memcache.getInstance().getValue(WxUtil.WX_TOKEN));
		
		JSONObject contentTemp = new JSONObject();
		contentTemp.put("content", content);
		
		JSONObject paramContent = new JSONObject();
		paramContent.put("touser", toOpenid);
		paramContent.put("msgtype", "text");
		paramContent.put("text", contentTemp);
		
		HttpTool http = new HttpApacheClient();
		JSONObject returns = http.wxHttpsPost(paramContent, url);
		log.info("pushMessage returns====> " + returns);

		return returns;
	}
	/**
	 * 这个接口是杉德的微信号
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void token(HttpServletRequest request, HttpServletResponse response)
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
		
		JSONObject xmlJson = XmlUtils.getDocumentFromRequest(request);
		// {"ToUserName":"gh_b35778044c48","FromUserName":"oUwjLwpYv0pkCC424mOxcBG24CVY","CreateTime":"1446189462","MsgType":"text","Content":"哦","MsgId":"6211336443510120964"}
		String returnStr = WxUtil.createMenu(xmlJson);
		
		System.out.println("响应的: " + returnStr);
		this.write(returnStr, CT.ENCODE_UTF8, response);
		return;
	}

	public void toTest(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("toTest: " + this.getParamMap(request));
		String token = WxUtil.getToken();
		String timestamp = String.valueOf(StringUtil.getTimestamp()); // 必填，生成签名的时间戳
		String nonceStr = UUID.randomUUID().toString(); // 必填，签名，见附录1
		String url = request.getRequestURL().toString();
		String signature = WxUtil.localSign(timestamp, nonceStr, url); // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		String appid = SmpPropertyUtil.getValue("wx", "appid");
		
		request.setAttribute("timestamp", timestamp);
		request.setAttribute("nonceStr", nonceStr);
		request.setAttribute("url", url);
		request.setAttribute("signature", signature);
		request.setAttribute("cacheToken", token);
		request.setAttribute("appId", appid);
		this.forward("/www/html/wxtest.jsp", request, response);
		return;
	}
	
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
