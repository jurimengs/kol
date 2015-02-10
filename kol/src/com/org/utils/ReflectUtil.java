package com.org.utils;

public class ReflectUtil {
	public static final String getCurrentMethodName(StackTraceElement[] e){
		String res = "";
		boolean doNext = false;
		for (StackTraceElement s : e) {
			if (doNext) {
	    	   res = s.getFileName().substring(0,s.getFileName().lastIndexOf("."))+"-"+s.getMethodName();
	    	   break ;
	    	}
			doNext = s.getMethodName().equals("getStackTrace");
	    }
		return res;
	}
}
