package com.org.controller.webapp.model;

import java.util.HashMap;
import java.util.Map;

import com.org.util.MD5;

public class WxMenu {
	private static WxMenu wxMenu = null;
	private static Map<String, String> menuMap = new HashMap<String, String>();
	private boolean isInit = false;
	private static String all = "";
	
	public static WxMenu getInstance() {
		if(wxMenu == null) {
			wxMenu = new WxMenu();
		}
		return wxMenu;
	}
	
	private WxMenu(){
		init();
	}
	
	public boolean containsMenu(String key) {
		return menuMap.containsKey(MD5.getMD5(key));
	}
	
	public void put(String key, String value){
		menuMap.put(MD5.getMD5(key), value);
	}
	
	public String get(String key){
		return menuMap.get(MD5.getMD5(key));
	}
	
	public String getAll(){
		return all;
	}
	
	public void init() {
		if(!isInit) {
			String ss = "人生百科";
			String aa = "toTest.do";
			String cc = "baidu";
			String ss_link = "<a href=\"http://www.rsbk.cc\">"+ss+"</a>";
			String aa_link = "<a href=\"http://www.rsbk.cc/wx/toTest.do \">"+aa+"</a>";
			String cc_link = "<a href=\"http://www.baidu.com\">"+cc+"</a>";
			menuMap.put(MD5.getMD5(ss), ss_link);
			menuMap.put(MD5.getMD5(aa), aa_link);
			menuMap.put(MD5.getMD5(cc), cc_link);
			
			all += ss_link;
			all += "\n";
			all += aa_link;
			all += "\n";
			all += cc_link;
			all += "\n";
		}
		isInit = true;
	}
	
}
