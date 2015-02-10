package com.org.utils;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

public class SmpMessageConverter implements MessageConverter {
	private static Log log = LogFactory.getLog(SmpMessageConverter.class);

	public Object fromMessage(Message message) throws JMSException,MessageConversionException {
		JSONObject jsonObject = null;
		if(message instanceof TextMessage){
			String value = ((TextMessage) message).getText();
			log.info("收到消息数据值："+ value);
			jsonObject = JSONUtils.getJsonFromString(value);
		}else if(message instanceof MapMessage){
			//TODO
		}
		return jsonObject;
	}

	public Message toMessage(Object object, Session session) throws JMSException,MessageConversionException {
		Message message = null;

		log.info("即将转化的数据："+ object.toString());
		
		if(object instanceof JSONObject){
			message = session.createTextMessage(((JSONObject)object).toString());
		}else{
			message = session.createTextMessage(JSONUtils.getJsonStringFromObject(object));	
		}
		return message;
	}

}
