package com.org.controller.webapp.rute;

import java.util.concurrent.Callable;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.org.controller.webapp.msgmanager.TypeEvent;
import com.org.controller.webapp.msgmanager.TypeImage;
import com.org.controller.webapp.msgmanager.TypeNews;
import com.org.controller.webapp.msgmanager.TypeText;

/**
 * 路由适配器，决定当前请求由谁来处理
 * @author Administrator
 *
 */
public class RuteAdapter {
	private static Log log = LogFactory.getLog(RuteAdapter.class);
	
	public static Callable<String> adapter(JSONObject xmlJson){
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

}
