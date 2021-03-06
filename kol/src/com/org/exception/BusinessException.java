package com.org.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public BusinessException(){
		super();
	}

	public BusinessException(String message){
		super(message);
	}
	
	public BusinessException(Throwable e){
		super(e);
	}
	
	public BusinessException(String message,Throwable e){
		super(message,e);
	}
	
	public void printStackTrace() {
		super.printStackTrace();
	}

}
