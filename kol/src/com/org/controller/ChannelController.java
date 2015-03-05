package com.org.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.org.services.ChannelService;
import com.org.util.SpringUtil;

import net.sf.json.JSONArray;


@Controller
@RequestMapping("/channel")
public class ChannelController {
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException, IOException{
		try {
			response.setHeader("Pragma","no-cache"); 
			response.setHeader("Cache-Control","no-cache"); 
			response.setDateHeader("Expires", 0); 
			/* 1.获得商户端请求的值  默认设置数据处理成功 */
			//JSONArray testimonialsArray = new JSONArray();
			ChannelService channelService = (ChannelService) SpringUtil.getBean("channelService");
			String limit = "20";
			JSONArray testimonialsArray = channelService.getTestimonialsByChannelId(null, limit);
			// 测试方法
			//AddDataByTest(testimonialsArray);
			log.info("收到请求参数：　" );
			// 假设查询到的永远只有100条数据，每列分25条数据
			request.getSession(true).setAttribute("testimonialsArray", testimonialsArray);
			request.getSession(true).setAttribute("ohmg", "true");
		} catch (Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}
		
		return "/index.jsp";
	}
	
	@RequestMapping("/life")
	public String life(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException, IOException{
		try {
			response.setHeader("Pragma","no-cache"); 
			response.setHeader("Cache-Control","no-cache"); 
			response.setDateHeader("Expires", 0); 
			/* 1.获得商户端请求的值  默认设置数据处理成功 */
			//JSONArray testimonialsArray = new JSONArray();
			ChannelService channelService = (ChannelService) SpringUtil.getBean("channelService");
			String channelId = "0";
			JSONArray testimonialsArray = channelService.getTestimonialsByChannelId(channelId);
			// 测试方法
			//AddDataByTest(testimonialsArray);
			log.info("收到请求参数：　" );
			// 假设查询到的永远只有100条数据，每列分25条数据
			request.getSession(true).setAttribute("testimonialsArray", testimonialsArray);
		} catch (Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}
		
		return "/index.jsp";
	}
	
	@RequestMapping("/emotion")
	public String emotion(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException, IOException{
		try {
			response.setHeader("Pragma","no-cache"); 
			response.setHeader("Cache-Control","no-cache"); 
			response.setDateHeader("Expires", 0); 
			/* 1.获得商户端请求的值  默认设置数据处理成功 */
			//JSONArray testimonialsArray = new JSONArray();
			ChannelService channelService = (ChannelService) SpringUtil.getBean("channelService");
			String channelId = "1";
			JSONArray testimonialsArray = channelService.getTestimonialsByChannelId(channelId);
			// 测试方法
			//AddDataByTest(testimonialsArray);
			log.info("收到请求参数：　" );
			// 假设查询到的永远只有100条数据，每列分25条数据
			request.getSession(true).setAttribute("testimonialsArray", testimonialsArray);
		} catch (Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}
		
		return "/index.jsp";
	}
	
	@RequestMapping("/career")
	public String career(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException, IOException{
		try {
			response.setHeader("Pragma","no-cache"); 
			response.setHeader("Cache-Control","no-cache"); 
			response.setDateHeader("Expires", 0); 
			/* 1.获得商户端请求的值  默认设置数据处理成功 */
			//JSONArray testimonialsArray = new JSONArray();
			ChannelService channelService = (ChannelService) SpringUtil.getBean("channelService");
			String channelId = "2";
			JSONArray testimonialsArray = channelService.getTestimonialsByChannelId(channelId);
			// 测试方法
			//AddDataByTest(testimonialsArray);
			log.info("收到请求参数：　" );
			// 假设查询到的永远只有100条数据，每列分25条数据
			request.getSession(true).setAttribute("testimonialsArray", testimonialsArray);
		} catch (Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}
		
		return "/index.jsp";
	}
	
	@RequestMapping("/other")
	public String other(HttpServletRequest request,HttpServletResponse response) 
			throws UnsupportedEncodingException, IOException{
		try {
			response.setHeader("Pragma","no-cache"); 
			response.setHeader("Cache-Control","no-cache"); 
			response.setDateHeader("Expires", 0); 
			/* 1.获得商户端请求的值  默认设置数据处理成功 */
			//JSONArray testimonialsArray = new JSONArray();
			ChannelService channelService = (ChannelService) SpringUtil.getBean("channelService");
			String channelId = "3";
			JSONArray testimonialsArray = channelService.getTestimonialsByChannelId(channelId);
			// 测试方法
			//AddDataByTest(testimonialsArray);
			log.info("收到请求参数：　" );
			// 假设查询到的永远只有100条数据，每列分25条数据
			request.getSession(true).setAttribute("testimonialsArray", testimonialsArray);
		} catch (Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}
		
		return "/index.jsp";
	}
	
	private Log log = LogFactory.getLog(ChannelController.class);
}
