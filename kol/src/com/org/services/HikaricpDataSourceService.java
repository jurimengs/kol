package com.org.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.org.Connection;
import com.org.connection.HikaricpConnection;
import com.org.container.CommonContainer;

/**
 * hikaricp数据源服务
 * @author Administrator
 *
 */
public class HikaricpDataSourceService extends CommonDataSourceService{
	
	public static HikaricpDataSourceService getInstance(){
		if(hds == null){
			hds = new HikaricpDataSourceService();
		}
		return hds;
	}

	@Override
	public Connection loadDataSourceByParam(JSONObject extendsParam) {
		log.info("HikaricpDataSourceService: 按参数加载Hikaricp连接");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection loadDefaultDataSource() {
		log.info("HikaricpDataSourceService: 加载默认Hikaricp连接");
		HikaricpConnection con = null;
		
		Properties pro = new Properties();
		try {
			String fileName = "/WEB-INF/config/oracle_db.properties";
			InputStream in = CommonContainer.getServletContext().getResourceAsStream(fileName);
			pro.load(in);
		} catch (IOException e1) {
			e1.printStackTrace();
			return con;
		}
		con = new HikaricpConnection(pro);
		return con;
	}
	
	private static HikaricpDataSourceService hds = null;
	private HikaricpDataSourceService(){
		super(HikaricpDataSourceService.class);
	}
	private Log log = LogFactory.getLog(HikaricpDataSourceService.class);
}
