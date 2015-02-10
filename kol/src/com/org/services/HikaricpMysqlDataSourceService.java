package com.org.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.org.Connection;
import com.org.connection.HikaricpMysqlConnection;
import com.org.container.CommonContainer;

/**
 * hikaricp数据源服务
 * @author Administrator
 *
 */
public class HikaricpMysqlDataSourceService extends CommonDataSourceService{
	
	public static HikaricpMysqlDataSourceService getInstance(){
		if(hds == null){
			hds = new HikaricpMysqlDataSourceService();
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
		log.info("HikaricpMysqlDataSourceService: 加载默认Hikaricp Mysql连接");
		HikaricpMysqlConnection con = null;
		
		Properties pro = new Properties();
		try {
			String fileName = "/WEB-INF/config/mysql_db.properties";
			InputStream in = CommonContainer.getServletContext().getResourceAsStream(fileName);
			pro.load(in);
		} catch (IOException e1) {
			e1.printStackTrace();
			return con;
		}
		String driverClassName = pro.getProperty("driverClassName");
		String serverName = pro.getProperty("serverName");
		String port = pro.getProperty("port");
		String databaseName = pro.getProperty("databaseName");
		String user = pro.getProperty("user");
		String password = pro.getProperty("password");
		String connectionTimeout = pro.getProperty("connectionTimeout");
		String maxLifetime = pro.getProperty("maxLifetime");
		String idleTimeout = pro.getProperty("idleTimeout");
		String maximumPoolSize = pro.getProperty("maximumPoolSize");
		String minimumIdle = pro.getProperty("minimumIdle");
		
		con = new HikaricpMysqlConnection(driverClassName, serverName, port, databaseName, user, password, 
				connectionTimeout, maxLifetime, idleTimeout, maximumPoolSize, minimumIdle);
		return con;
	}
	
	private static HikaricpMysqlDataSourceService hds = null;
	private HikaricpMysqlDataSourceService(){
		super(HikaricpMysqlDataSourceService.class);
	}
	private Log log = LogFactory.getLog(HikaricpMysqlDataSourceService.class);
}
