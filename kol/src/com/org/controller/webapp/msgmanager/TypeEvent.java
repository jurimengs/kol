package com.org.controller.webapp.msgmanager;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.org.controller.webapp.WxConstant;
import com.org.controller.webapp.utils.WxUserContainer;
import com.org.controller.webapp.utils.WxUtil;

/**
 * request from wx , type is "event"
 * @author Administrator
 *
 */
public class TypeEvent extends MessageManager implements Event {
	private Log log = LogFactory.getLog(TypeEvent.class);
	private JSONObject xmlJson;

	public TypeEvent(JSONObject xmlJson) {
		this.xmlJson = xmlJson;
	}

	@Override
	public String deal() {
		String FromUserName = xmlJson.getString("FromUserName");
		String Event = xmlJson.getString("Event");
		String EventKey = xmlJson.getString("EventKey"); // 对应自定义的key 值
		log.info("EventKey ====>" + EventKey);
		// 拿事件类型 和 点击的按钮key值判断 可以决定业务类型
		if(Event.equals("CLICK")) {
			log.info("消息推送开始");
			JSONObject returns = null;
			JSONObject paramContent = new JSONObject();
			if(WxUtil.ENTER_CHATING_ROOM.equals(EventKey)) {
				// 加入聊天室
				WxUserContainer.joininChatingRoom(FromUserName);
				
				JSONObject contentTemp = new JSONObject();
				contentTemp.put("content", "您已进入聊天室, 可以和大家聊天啦");
				paramContent.put("touser", FromUserName);
				paramContent.put("msgtype", "text");
				paramContent.put("text", contentTemp);
				
				returns = pushMessage(paramContent, "wx_send_message_by_service");
			} else if(WxUtil.EXIT_CHATING_ROOM.equals(EventKey)) {
				// 加入聊天室
				WxUserContainer.exitChatingRoom(FromUserName);
				JSONObject contentTemp = new JSONObject();
				contentTemp.put("content", "您已退出聊天室");
				
				paramContent.put("touser", FromUserName);
				paramContent.put("msgtype", "text");
				paramContent.put("text", contentTemp);
			}
			
			returns = pushMessage(paramContent, "wx_send_message_by_service");
			if(returns != null && (returns.getInt("errcode")==0)) {
				log.info("消息推送成功");
			}
		}
		return WxConstant.RETURN_SUCCESS;
	}

	public JSONObject getXmlJson() {
		return xmlJson;
	}

	public void setXmlJson(JSONObject xmlJson) {
		this.xmlJson = xmlJson;
	}
	
}
