package com.org.controller.webapp.rute;

import java.util.Map;
import java.util.concurrent.Callable;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.org.controller.webapp.utils.WxUserContainer;
import com.org.controller.webapp.utils.WxUtil;

/**
 * request from wx , type is "news"
 * @author Administrator
 *
 */
public class TypeNews implements Business<String> {
	private Log log = LogFactory.getLog(TypeNews.class);
	private JSONObject xmlJson;

	public TypeNews(JSONObject xmlJson) {
		this.xmlJson = xmlJson;
	}

	@Override
	public String call() {
		String returnStr = "";
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
			JSONArray chatingUserArray = WxUserContainer.getChatingUser();
			// 从组中除去发信息者自己
			chatingUserArray.remove(msgFromOpenid);
			log.info("openidList ====>" + chatingUserArray);
			
			String content = nick + "说:\n"+xmlJson.getString("Content");
			// 每个人都要收到同样一个消息
			JSONObject paramContent = MessageUtil.getTextMessageJson(content);
			// 从0开始递归发送，实现群发
			MessageUtil.pushMassMessage(chatingUserArray, paramContent, 0);
		} else {
			returnStr = WxUtil.autoReply(xmlJson);
		}
		return returnStr;
	}
	
	public JSONObject getXmlJson() {
		return xmlJson;
	}

	public void setXmlJson(JSONObject xmlJson) {
		this.xmlJson = xmlJson;
	}
	
}
