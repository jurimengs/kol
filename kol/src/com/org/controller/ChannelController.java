package com.org.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

import com.org.common.CommonConstant;
import com.org.services.busi.ChannelService;
import com.org.services.busi.CommemorateService;
import com.org.servlet.CommonController;
import com.org.servlet.SmpHttpServlet;
import com.org.util.SpringUtil;

@Controller
public class ChannelController extends SmpHttpServlet implements CommonController{
	private static final long serialVersionUID = 2156792239072761671L;

	public ChannelController(){
		
	}
	
	public void home(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		log.info("index。。。" );
		HttpSession session = request.getSession(true);
		session.setAttribute(CommonConstant.CHANNEL_NAME, "首页");
		session.setAttribute(CommonConstant.CURRENT_CHANNEL_ID, CommonConstant.HOME);
		
		String t_limit = "100";
		ChannelService channelService = (ChannelService) SpringUtil.getBean("channelService");
		JSONArray testimonialsArray = channelService.getTestimonialsByChannelId(null, t_limit);
		
		// 纪念板的第一个
		CommemorateService commemorateService = (CommemorateService) SpringUtil.getBean("commemorateService");
		JSONArray commemorateArray = commemorateService.getCurrentCommemorate("1");
		if(commemorateArray.size() > 0){
			session.setAttribute("commemorate", commemorateArray.getJSONObject(0));
		}
		
		// 假设查询到的永远只有100条数据，每列分25条数据
		session.setAttribute("testimonialsArray", testimonialsArray);
		session.setAttribute("ohmg", "true");
		
		this.redirect("/home.jsp", response);
		return;
	}
	
	public void life(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		log.info("life。。。" );
		HttpSession session = request.getSession(true);
		session.setAttribute(CommonConstant.CHANNEL_NAME, "生活");
		session.setAttribute(CommonConstant.CURRENT_CHANNEL_ID, CommonConstant.LIFE);
		
		ChannelService channelService = (ChannelService) SpringUtil.getBean("channelService");
		JSONArray testimonialsArray = channelService.getTestimonialsByChannelId(CommonConstant.LIFE);
		session.setAttribute("testimonialsArray", testimonialsArray);

		this.redirect("/channel/life.jsp", response);
		return;
	}
	
	public void emotion(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		log.info("emotion。。。" );
		HttpSession session = request.getSession(true);
		session.setAttribute(CommonConstant.CHANNEL_NAME, "情感");
		session.setAttribute(CommonConstant.CURRENT_CHANNEL_ID, CommonConstant.EMOTION);
		
		ChannelService channelService = (ChannelService) SpringUtil.getBean("channelService");
		JSONArray testimonialsArray = channelService.getTestimonialsByChannelId(CommonConstant.EMOTION);
		session.setAttribute("testimonialsArray", testimonialsArray);

		this.redirect("/channel/emotion.jsp", response);
		return;
	}
	
	public void career(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		log.info("career。。。" );
		HttpSession session = request.getSession(true);
		session.setAttribute(CommonConstant.CHANNEL_NAME, "职场");
		session.setAttribute(CommonConstant.CURRENT_CHANNEL_ID, CommonConstant.CAREER);
		
		ChannelService channelService = (ChannelService) SpringUtil.getBean("channelService");
		JSONArray testimonialsArray = channelService.getTestimonialsByChannelId(CommonConstant.CAREER);
		session.setAttribute("testimonialsArray", testimonialsArray);

		this.redirect("/channel/career.jsp", response);
		return;
	}
	
	public void other(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		log.info("other。。。" );
		HttpSession session = request.getSession(true);
		session.setAttribute(CommonConstant.CHANNEL_NAME, "其他");
		session.setAttribute(CommonConstant.CURRENT_CHANNEL_ID, CommonConstant.OTHER);
		
		ChannelService channelService = (ChannelService) SpringUtil.getBean("channelService");
		JSONArray testimonialsArray = channelService.getTestimonialsByChannelId(CommonConstant.OTHER);
		session.setAttribute("testimonialsArray", testimonialsArray);

		this.redirect("/channel/other.jsp", response);
		return;
	}
	
	/*
	 * 纪念板
	 */
	public void commemorateBoard(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{
		log.info("纪念板。。。" );
		HttpSession session = request.getSession(true);
		session.setAttribute(CommonConstant.CHANNEL_NAME, "纪念板");
		session.setAttribute(CommonConstant.CURRENT_CHANNEL_ID, CommonConstant.COMMEMORATE_BOARD);
		
		CommemorateService commemorateService = (CommemorateService) SpringUtil.getBean("commemorateService");
		JSONArray resultArray = commemorateService.getLimitCommemorate("50");
		session.setAttribute("commemorateArray", resultArray);

		this.redirect("/channel/commemorateBoard.jsp", response);
		return;
	}
	
	private Log log = LogFactory.getLog(ChannelController.class);

	@Override
	public void post(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
	}
}
