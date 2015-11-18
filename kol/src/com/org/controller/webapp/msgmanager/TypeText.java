package com.org.controller.webapp.msgmanager;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.org.controller.webapp.utils.WxUserContainer;
import com.org.controller.webapp.utils.WxUtil;

/**
 * request from wx , type is "text"
 * @author Administrator
 *
 */
public class TypeText extends MessageManager implements Event {
	private Log log = LogFactory.getLog(TypeText.class);
	private JSONObject xmlJson;

	public TypeText(JSONObject xmlJson) {
		this.xmlJson = xmlJson;
	}

	@Override
	public String deal() {
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
		return returnStr;
	}

	public JSONObject getXmlJson() {
		return xmlJson;
	}

	public void setXmlJson(JSONObject xmlJson) {
		this.xmlJson = xmlJson;
	}
	
}
