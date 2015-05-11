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
	protected Map<String, Connection<?>> sourceMap= new HashMap<String, Connection<?>>();
	protected static Connection<?> currentConnection = null;

	/**
	 * 切换不同数据源链接
	 * @param dataSourceId
	 * @return
	 */
	public Connection<?> switchConnectionType(String connectionName){
		if(sourceMap.containsKey(connectionName)){
			Connection<?> ds = sourceMap.get(connectionName);
			if(ds != null){
				//ConnectionManager.getInstance().setDataSource(ds);
				currentConnection = ds;
				log.info("切换数据源连接类型成功:" + connectionName);
			} else {
				log.info("切换数据源连接类型失败，不存在数据源:" + connectionName);
			}
		}
		return currentConnection;
	}
	
	public Connection<?> getCurrentConnection(){
		if(currentConnection == null){
			currentConnection = sourceMap.get(CommonConstant.DB_MYSQL);
		}
		return currentConnection;
	}
	
	public Connection<?> getConnection(String key){
		return sourceMap.get(key);
	}
	
	public void initAllDataSource(){
//		HikaricpDataSourceService.getInstance().loadDataSource(CommonConstant.DB_HIKARICP, null);
//		MongodbDataSourceService.getInstance().loadDataSource(CommonConstant.DB_MONGO, null);
		HikaricpMysqlDataSourceService.getInstance().loadDataSource(CommonConstant.DB_MYSQL, null);
	}
	
	public static DataSourceContainer getInstance(){
		if(container == null){
			container = new DataSourceContainer();
		}
		return container;
	}
	
	public void store(String connectionName, Connection<?> con) {
		sourceMap.put(connectionName, con);
	}
	
	private DataSourceContainer(){}
	private Log log = LogFactory.getLog(DataSourceContainer.class);
	private static DataSourceContainer container = null;

	
}
