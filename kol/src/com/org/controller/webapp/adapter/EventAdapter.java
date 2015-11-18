package com.org.controller.webapp.adapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.org.controller.webapp.msgmanager.Event;
import com.org.controller.webapp.msgmanager.TypeEvent;
import com.org.controller.webapp.msgmanager.TypeText;

import net.sf.json.JSONObject;

/**
 * try to determined whitch event will deal this request
 * @author Administrator
 *
 */
public class EventAdapter {
	private Log log = LogFactory.getLog(EventAdapter.class);
	// request parameter from wx
	private JSONObject xmlJson;
	
	public EventAdapter(JSONObject xmlJson) {
		this.xmlJson = xmlJson;
	}
	
	public Event adapter(){
		if(xmlJson == null) {
			throw new NullPointerException("EventAdapter can not deal an null param request");
		}
		Event e = null;
		String msgType = xmlJson.getString("MsgType");
		log.info("请求消息类型====>"+msgType);
		if(msgType.equals("event")) {
			e = new TypeEvent(xmlJson);
		} else if(msgType.equals("text")) {
			e = new TypeText(xmlJson);
		} else {
			throw new NullPointerException("unknown event type : " + msgType);
		}
		return e;
	}

	public JSONObject getXmlJson() {
		return xmlJson;
	}

	public void setXmlJson(JSONObject xmlJson) {
		this.xmlJson = xmlJson;
	}
	
	
}
