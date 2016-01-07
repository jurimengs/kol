package com.org.controller.webapp.msgmanager;

import java.util.Map;
import java.util.concurrent.Callable;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.org.controller.webapp.WxConstant;
import com.org.controller.webapp.utils.WxUserContainer;
import com.org.controller.webapp.utils.WxUtil;
import com.org.utils.SmpPropertyUtil;

/**
 * request from wx , type is "text"
 * @author Administrator
 *
 */
public class BatchSendMessage extends ServiceMessageManager implements Callable<String> {
	//private Log log = LogFactory.getLog(TypeText.class);
	private String url = SmpPropertyUtil.getValue("wx", "wx_send_message_by_service");
	private JSONObject paramContent;

	public BatchSendMessage(JSONObject paramContent) {
		this.paramContent = paramContent;
	}

	@Override
	public String call() {
		pushMessage(paramContent, url);
		return null;
	}
	
	public void setReturn(){
		
	}
}
