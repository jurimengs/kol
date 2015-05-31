package com.org.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.org.common.CommonConstant;
import com.org.services.busi.ChannelService;
import com.org.services.busi.CommemorateService;
import com.org.util.SpringUtil;


@Controller
@RequestMapping("/channel")
public class ChannelController {
	
	@RequestMapping("/home")
	public String home(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException, IOException{
		HttpSession session = request.getSession(true);
		session.setAttribute(CommonConstant.CHANNEL_NAME, "首页");
		try {
			log.info("index。。。" );
			response.setHeader("Pragma","no-cache"); 
			response.setHeader("Cache-Control","no-cache"); 
			response.setDateHeader("Expires", 0); 
			String channelId = CommonConstant.HOME;
			session.setAttribute(CommonConstant.CURRENT_CHANNEL_ID, channelId);
			
			//JSONArray testimonialsArray = new JSONArray();
			ChannelService channelService = (ChannelService) SpringUtil.getBean("channelService");
			String t_limit = "100";
			JSONArray testimonialsArray = channelService.getTestimonialsByChannelId(null, t_limit);
			
			// 纪念板的第一个
			CommemorateService commemorateService = (CommemorateService) SpringUtil.getBean("commemorateService");
			JSONArray commemorateArray = commemorateService.getLimitCommemorate("1");
			if(commemorateArray.size() > 0){
				session.setAttribute("commemorate", commemorateArray.getJSONObject(0));
			} else {
				session.setAttribute("commemorate","{}");
			}
			
			// 测试方法
			//AddDataByTest(testimonialsArray);
			log.info("收到请求参数：　" );
			// 假设查询到的永远只有100条数据，每列分25条数据
			session.setAttribute("testimonialsArray", testimonialsArray);
			session.setAttribute("ohmg", "true");
		} catch (Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}
		
		return "/home.jsp";
	}
	
	@RequestMapping("/life")
	public String life(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException, IOException{
		request.getSession(true).setAttribute(CommonConstant.CHANNEL_NAME, "生活");
		try {
			log.info("life。。。" );
			response.setHeader("Pragma","no-cache"); 
			response.setHeader("Cache-Control","no-cache"); 
			response.setDateHeader("Expires", 0); 
			String channelId = CommonConstant.LIFE;
			request.getSession(true).setAttribute(CommonConstant.CURRENT_CHANNEL_ID, channelId);
			
			//JSONArray testimonialsArray = new JSONArray();
			ChannelService channelService = (ChannelService) SpringUtil.getBean("channelService");
			JSONArray testimonialsArray = channelService.getTestimonialsByChannelId(channelId);
			// 测试方法
			//AddDataByTest(testimonialsArray);
			// 假设查询到的永远只有100条数据，每列分25条数据
			request.getSession(true).setAttribute("testimonialsArray", testimonialsArray);
		} catch (Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}
		
		return "/channel/life.jsp";
	}
	
	@RequestMapping("/emotion")
	public String emotion(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException, IOException{
		request.getSession(true).setAttribute(CommonConstant.CHANNEL_NAME, "情感");
		try {
			log.info("emotion。。。" );
			response.setHeader("Pragma","no-cache"); 
			response.setHeader("Cache-Control","no-cache"); 
			response.setDateHeader("Expires", 0); 
			String channelId = CommonConstant.EMOTION;
			request.getSession(true).setAttribute(CommonConstant.CURRENT_CHANNEL_ID, channelId);
			
			//JSONArray testimonialsArray = new JSONArray();
			ChannelService channelService = (ChannelService) SpringUtil.getBean("channelService");
			JSONArray testimonialsArray = channelService.getTestimonialsByChannelId(channelId);
			// 测试方法
			//AddDataByTest(testimonialsArray);
			// 假设查询到的永远只有100条数据，每列分25条数据
			request.getSession(true).setAttribute("testimonialsArray", testimonialsArray);
		} catch (Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}
		
		return "/channel/emotion.jsp";
	}
	
	@RequestMapping("/career")
	public String career(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException, IOException{
		request.getSession(true).setAttribute(CommonConstant.CHANNEL_NAME, "职场");
		try {
			log.info("career。。。" );
			response.setHeader("Pragma","no-cache"); 
			response.setHeader("Cache-Control","no-cache"); 
			response.setDateHeader("Expires", 0); 
			String channelId = CommonConstant.CAREER;
			request.getSession(true).setAttribute(CommonConstant.CURRENT_CHANNEL_ID, channelId);
			
			//JSONArray testimonialsArray = new JSONArray();
			ChannelService channelService = (ChannelService) SpringUtil.getBean("channelService");
			JSONArray testimonialsArray = channelService.getTestimonialsByChannelId(channelId);
			//AddDataByTest(testimonialsArray);
			// 假设查询到的永远只有100条数据，每列分25条数据
			request.getSession(true).setAttribute("testimonialsArray", testimonialsArray);
		} catch (Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}
		
		return "/channel/career.jsp";
	}
	
	@RequestMapping("/other")
	public String other(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException, IOException{
		request.getSession(true).setAttribute(CommonConstant.CHANNEL_NAME, "其他");
		try {
			log.info("other。。。" );
			response.setHeader("Pragma","no-cache"); 
			response.setHeader("Cache-Control","no-cache"); 
			response.setDateHeader("Expires", 0); 
			String channelId = CommonConstant.OTHER;
			request.getSession(true).setAttribute(CommonConstant.CURRENT_CHANNEL_ID, channelId);
			
			//JSONArray testimonialsArray = new JSONArray();
			ChannelService channelService = (ChannelService) SpringUtil.getBean("channelService");
			JSONArray testimonialsArray = channelService.getTestimonialsByChannelId(channelId);
			// 测试方法
			//AddDataByTest(testimonialsArray);
			// 假设查询到的永远只有100条数据，每列分25条数据
			request.getSession(true).setAttribute("testimonialsArray", testimonialsArray);
		} catch (Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}
		
		return "/channel/other.jsp";
	}
	
	/*
	 * 纪念板
	 */
	@RequestMapping("/commemorateBoard")
	public String commemorateBoard(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException, IOException{
		request.getSession(true).setAttribute(CommonConstant.CHANNEL_NAME, "纪念板");
		try {
			log.info("纪念板。。。" );
			response.setHeader("Pragma","no-cache"); 
			response.setHeader("Cache-Control","no-cache"); 
			response.setDateHeader("Expires", 0);
			String channelId = CommonConstant.COMMEMORATE_BOARD;
			request.getSession(true).setAttribute(CommonConstant.CURRENT_CHANNEL_ID, channelId);
			
			CommemorateService commemorateService = (CommemorateService) SpringUtil.getBean("commemorateService");
			JSONArray resultArray = commemorateService.getLimitCommemorate("50");
			request.getSession(true).setAttribute("commemorateArray", resultArray);
		} catch (Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}
		
		return "/channel/commemorateBoard.jsp";
	}
	
	private Log log = LogFactory.getLog(ChannelController.class);
}
