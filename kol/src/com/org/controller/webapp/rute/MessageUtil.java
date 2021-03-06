package com.org.controller.webapp.rute;

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
 * 客服消息管理
 * 总管消息封装、消息发送
 * service message send manager interface 
 * @author Administrator
 *
 */
public class MessageUtil {
	
	private static String url = SmpPropertyUtil.getValue("wx", "wx_send_message_by_service");
	private static Log log = LogFactory.getLog(MessageUtil.class);
	protected MessageUtil(){}

	/**
	 * 发送消息给指定用户
	 * @param paramContent json体，包含了用户openid、content的
	 * @return
	 */
	public static JSONObject sendToOne(String content, String openid){
		JSONObject paramContent = getTextMessageJson(content);
		paramContent.put("touser", openid);

		// 调用客服接口发送消息 
		String urlTemp = url.concat(Memcache.getInstance().getValue(WxUtil.WX_TOKEN));
		log.info("pushMessage url====> " + urlTemp);
		
		
		HttpTool http = new HttpApacheClient();
		JSONObject returns = http.wxHttpsPost(paramContent, urlTemp);
		log.info("pushMessage returns====> " + returns);

		return returns;
	}
	
	/**
	 * 发送消息.为批量发送做的
	 * @param paramContent json体，包含了用户openid、content的
	 * @return
	 */
	public static JSONObject pushMessage(JSONObject paramContent){

		// 调用客服接口发送消息 
		String urlTemp = url.concat(Memcache.getInstance().getValue(WxUtil.WX_TOKEN));
		log.info("pushMessage url====> " + urlTemp);
		
		
		HttpTool http = new HttpApacheClient();
		JSONObject returns = http.wxHttpsPost(paramContent, urlTemp);
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
	public static void pushMassMessage(JSONArray openidList, JSONObject paramContent, int nextOpenid){
		if(nextOpenid >= openidList.size()) {
			log.info("递归完成");
			return ;
		}
		
		String toOpenid = openidList.getString(nextOpenid);
		paramContent.put("touser", toOpenid);
		
		// 调用客服接口发送消息 
		JSONObject pushResult = pushMessage(paramContent);
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
	public static JSONObject getTextMessageJson(String content){
		JSONObject contentTemp = new JSONObject();
		contentTemp.put("content", content);
		
		JSONObject paramContent = new JSONObject();
		paramContent.put("msgtype", "text");
		paramContent.put("text", contentTemp);
		return paramContent;
	}
	
	/**
	 * 编辑图片消息模板
	 * @param mediaId 
	 * @return
	 */
	public static JSONObject getImageMessageJson(String mediaId){
		JSONObject imageTemp = new JSONObject();
		imageTemp.put("media_id", mediaId);
		
		JSONObject paramContent = new JSONObject();
		paramContent.put("msgtype", "image");
		paramContent.put("image", imageTemp);
		
		return paramContent;
	}
	
	/**
	 * 编辑图文消息模板
	 * 只能发送10条（什么意思？每个人只能发送10条？）
	 * @param articles : 
	 * "articles": [
		         {
		             "title":"Happy Day",
		             "description":"Is Really A Happy Day",
		             "url":"URL",
		             "picurl":"PIC_URL"
		         },
		         {
		             "title":"Happy Day",
		             "description":"Is Really A Happy Day",
		             "url":"URL",
		             "picurl":"PIC_URL"
		         }
	    ]
	 * @return
	 */
	public static JSONObject getNewsMessageJson(JSONArray articles){
		/*{
		    "msgtype":"news",
		    "news":{
		        "articles": [
		         {
		             "title":"Happy Day",
		             "description":"Is Really A Happy Day",
		             "url":"URL",
		             "picurl":"PIC_URL"
		         },
		         {
		             "title":"Happy Day",
		             "description":"Is Really A Happy Day",
		             "url":"URL",
		             "picurl":"PIC_URL"
		         }
		         ]
		    }
		}*/
		
		JSONObject news = new JSONObject();
		news.put("articles", articles);
		
		JSONObject paramContent = new JSONObject();
		paramContent.put("msgtype", "news");
		paramContent.put("news", news);
		return paramContent;
	}
}
