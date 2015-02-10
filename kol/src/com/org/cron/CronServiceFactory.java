package com.org.cron;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.org.utils.SmpContext;

public class CronServiceFactory {
	private Log log = LogFactory.getLog(CronServiceFactory.class);
	
	private static CronServiceFactory factory;
	
	private CronServiceFactory(){
		
	}
	
	public static CronServiceFactory getInstance(){
		if(factory == null)
			factory = new CronServiceFactory();
		return factory;
	}
	
	public CronService  createCronService(String beanName){
		CronService cs = null;
		cs = (CronService)SmpContext.getInstance().getBean(beanName);
		return cs;
	}


	
	
}
