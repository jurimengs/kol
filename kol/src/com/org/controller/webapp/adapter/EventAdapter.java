package com.org.controller.webapp.adapter;

import java.util.concurrent.Callable;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.org.controller.webapp.msgmanager.TypeEvent;
import com.org.controller.webapp.msgmanager.TypeImage;
import com.org.controller.webapp.msgmanager.TypeNews;
import com.org.controller.webapp.msgmanager.TypeText;

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
	
	public Callable<?> adapter(){
		if(xmlJson == null) {
			throw new NullPointerException("EventAdapter can not deal an null param request");
		}
		Callable<?> e = null;
		String msgType = xmlJson.getString("MsgType");
		log.info("请求消息类型====>"+msgType);
		if(msgType.equals("event")) {
			e = new TypeEvent(xmlJson);
		} else if(msgType.equals("text")) {
			e = new TypeText(xmlJson);
		} else if(msgType.equals("image")) {
			e = new TypeImage(xmlJson);
		} else if(msgType.equals("news")) {
			e = new TypeNews(xmlJson);
		} else {
			throw new NullPointerException("unknown event type : " + msgType);
		}
		return e;
	}


	
	public Callable<String> adapterThread(){
		if(xmlJson == null) {
			throw new NullPointerException("EventAdapter can not deal an null param request");
		}
		Callable<String> e = null;
		String msgType = xmlJson.getString("MsgType");
		log.info("请求消息类型====>"+msgType);
		if(msgType.equals("event")) {
			e = new TypeEvent(xmlJson);
		} else if(msgType.equals("text")) {
			e = new TypeText(xmlJson);
		} else if(msgType.equals("image")) {
			e = new TypeImage(xmlJson);
		} else if(msgType.equals("news")) {
			e = new TypeNews(xmlJson);
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
