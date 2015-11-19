package com.org.controller.webapp.msgmanager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.org.controller.webapp.utils.Memcache;
import com.org.controller.webapp.utils.WxUtil;
import com.org.utils.SmpPropertyUtil;
import com.org.utils.http.HttpTool;
import com.org.utils.http.impl.HttpApacheClient;

/**
 * 被动回复消息管理接口
 * passive reply message manager interface 
 * @author Administrator
 *
 */
// TODO 
public class PassiveReplyMessageManager {
	private Log log = LogFactory.getLog(PassiveReplyMessageManager.class);
	protected PassiveReplyMessageManager(){}

	/**
	 * 发送消息给指定用户
	 * @param toOpenid
	 * @param content
	 * @return
	 */
	public JSONObject pushMessage(JSONObject paramContent, String urlKey){

		// 调用客服接口发送消息 
		String url = SmpPropertyUtil.getValue("wx", urlKey);
		url = url.concat(Memcache.getInstance().getValue(WxUtil.WX_TOKEN));
		
		HttpTool http = new HttpApacheClient();
		JSONObject returns = http.wxHttpsPost(paramContent, url);
		log.info("pushMessage returns====> " + returns);

		return returns;
	}

	/**
	 * 递归发送消息给用户列表中的用户
	 * @param openidList
	 * @param content
	 * @param nextOpenid
	 * @return
	 */
	public void pushMassMessage(JSONArray openidList, JSONObject paramContent, int nextOpenid){
		if(nextOpenid >= openidList.size()) {
			log.info("递归完成");
			return ;
		}
		
		String toOpenid = openidList.getString(nextOpenid);
		paramContent.put("touser", toOpenid);
		
		// 调用客服接口发送消息 
		String url = SmpPropertyUtil.getValue("wx", "wx_send_message_by_service");
		url = url.concat(Memcache.getInstance().getValue(WxUtil.WX_TOKEN));
		
		HttpTool http = new HttpApacheClient();
		JSONObject pushResult = http.wxHttpsPost(paramContent, url);
		if(pushResult.getString("errcode").equals("0")) {
			// 推送成功
			log.info("客服接口消息发送成功，用户id：" + toOpenid);
		}
		// 下一个openid的索引
		nextOpenid ++;
		// 递归发送
		pushMassMessage(openidList, paramContent, nextOpenid);

	}
	
	/**
	 * 编辑文本消息模板
	 * @param content
	 * @return
	 */
	public JSONObject getTextMessageJson(String content){
		JSONObject contentTemp = new JSONObject();
		contentTemp.put("content", content);
		
		JSONObject paramContent = new JSONObject();
		paramContent.put("msgtype", "text");
		paramContent.put("text", contentTemp);
		return paramContent;
	}
	
	/**
	 * 编辑图文消息模板
	 * @return
	 */
	public JSONObject getNewsMessageJson(String content){
		JSONObject contentTemp = new JSONObject();
		contentTemp.put("content", content);
		
		JSONObject paramContent = new JSONObject();
		paramContent.put("msgtype", "text");
		paramContent.put("text", contentTemp);
		return paramContent;
	}
}
