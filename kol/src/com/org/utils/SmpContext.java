package com.org.utils;


import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import com.google.inject.Injector;

public class SmpContext {
	
	private static Log log = LogFactory.getLog(SmpContext.class);

	private static SmpContext context;
	
	//Spring Container
	private  ApplicationContext applicationContext;
	
	//Web Container
	private ServletContext sc;
	
	//Guice Container For IOC
	private Injector injector ;
	
	private SmpContext(){
		
	}
	
	public void initApplicationContext(ApplicationContext applicationContext,ServletContext sc){
		this.applicationContext = applicationContext;
		this.sc = sc;
	}
	
	public void initGuice(Injector injector){
		this.injector = injector;
	}
	
	public ServletContext getServletContext(){
		return this.sc;
	}
	
	public static SmpContext getInstance(){
		if(context == null)
			context = new SmpContext();
		return context;
	}
	
	public Object getBean(String name){
		Object object = null;
		if(applicationContext != null){
			try{
				object = applicationContext.getBean(name);
			}catch(BeansException be){
				be.printStackTrace();
			}
		}else{
			log.info("Spring Contaner Is Null...");
		}
		return object;	
	}
	
	public <T> T getInstance(Class<T> clazz){
		T t = null;
		if(this.injector != null){
			try{
				t = this.injector.getInstance(clazz);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			log.info("Spring Contaner Is Null...");
		}
		return t;
	}

}
