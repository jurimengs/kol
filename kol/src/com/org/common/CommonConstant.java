package com.org.common;

public class CommonConstant {
	
	public static String ENCODE_DEFAULT = "utf-8";
	public final static String DB_MONGO = "mongo";
	public final static String DB_MYSQL = "hikaricp-mysql";
	public final static String DB_HIKARICP = "hikaricp-oracle";
	public final static String RESP_CODE = "respCode";
	public final static String RESP_MSG = "respMsg";
	public static final Integer ASC = -1;
	//　如果分页的条数超过 50000了，将对查询进行优化，这个量是用于区分分页条数是否超标
	public static final int LARGE_RECORD = 50000;
	//public static final int LARGE_RECORD = 50000;

	/**
	 * 用户session键名
	 */
	public final static String SESSION_USER = "sessionUser";
}
