package com.org.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.org.services.DataSourceContainer;

@Controller
@RequestMapping("/dsController")
public class DatasourceController {
	
	@RequestMapping("/switch")
	public void start(HttpServletRequest request,HttpServletResponse response){
		String dataSourceId = request.getParameter("dataSourceId");
		DataSourceContainer.getInstance().switchDataSource(dataSourceId);
	}
	
	@RequestMapping("/dsList")
	public String dsList(HttpServletRequest request,HttpServletResponse response){
		JSONArray arr = new JSONArray();
		
		JSONObject ds = new JSONObject();
		ds.put("dataSourceId", "mongo");
		ds.put("dataSourceShowName", "mongo数据");
		arr.add(ds);
		
		JSONObject ds2 = new JSONObject();
		ds2.put("dataSourceId", "hikaricp");
		ds2.put("dataSourceShowName", "hikaricp数据");
		arr.add(ds2);
		
		request.getSession().setAttribute("dsList", arr);
		return "/main/main.jsp";
	}
}
