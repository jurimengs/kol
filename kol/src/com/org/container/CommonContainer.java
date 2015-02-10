package com.org.container;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

public class CommonContainer {
	public static Map<Object, Object> map = new HashMap<Object, Object>();

	public static void saveContext(ServletContext servletContext) {
		map.put("servletContext", servletContext);
	}

	public static ServletContext getServletContext(){
		if(map.get("servletContext") != null){
			return (ServletContext)map.get("servletContext");
		}
		return null;
	}
}
