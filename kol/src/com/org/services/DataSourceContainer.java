package com.org.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.org.Connection;
import com.org.common.CommonConstant;

/**
 * 数据源服务
 * @author Administrator
 *
 */
public class DataSourceContainer {
	protected Map<String, Connection> sourceMap= new HashMap<String, Connection>();
	protected static Connection currentConnection = null;

	public Connection switchDataSource(String dataSourceId){
		if(sourceMap.containsKey(dataSourceId)){
			Connection ds = sourceMap.get(dataSourceId);
			if(ds != null){
				//ConnectionManager.getInstance().setDataSource(ds);
				currentConnection = ds;
				log.info("切换数据源成功:" + dataSourceId);
			} else {
				log.info("切换数据源失败，不存在数据源:" + dataSourceId);
			}
		}
		return currentConnection;
	}
	
	public Connection getCurrentConnection(){
		if(currentConnection == null){
			currentConnection = sourceMap.get(CommonConstant.DB_MONGO);
		}
		return currentConnection;
	}
	
	public Connection getConnection(String key){
		return sourceMap.get(key);
	}
	
	public void initAllDataSource(){
		HikaricpDataSourceService.getInstance().loadDataSource(CommonConstant.DB_HIKARICP, null);
		MongodbDataSourceService.getInstance().loadDataSource(CommonConstant.DB_MONGO, null);
		HikaricpMysqlDataSourceService.getInstance().loadDataSource(CommonConstant.DB_MYSQL, null);
	}
	
	public static DataSourceContainer getInstance(){
		if(container == null){
			container = new DataSourceContainer();
		}
		return container;
	}
	
	public void store(String dataSourceName, Connection ds) {
		sourceMap.put(dataSourceName, ds);
	}
	
	private DataSourceContainer(){}
	private Log log = LogFactory.getLog(DataSourceContainer.class);
	private static DataSourceContainer container = null;

	
}
