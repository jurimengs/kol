package com.org.services;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.org.Connection;

/**
 * hikaricp数据源服务
 * @author Administrator
 *
 */
public abstract class CommonDataSourceService {
	/**
	 * 重新加载数据源
	 * @param dataSourceName
	 * @param extendsParam 连接信息等
	 */
	public Connection loadDataSource(String dataSourceName, JSONObject extendsParam){
		// 执行reload操作
		log.info("加载"+dataSourceName+"数据源");
		Connection ds = null;
		if(extendsParam == null || extendsParam.isNullObject()){
			ds = loadDefaultDataSource();
		} else {
			// 按extendsParam加载数据源
			ds = loadDataSourceByParam(extendsParam);
		}
		// 存储当前source到容器
		DataSourceContainer.getInstance().store(dataSourceName, ds);
		return ds;
	}
	
	public abstract Connection loadDataSourceByParam(JSONObject extendsParam);

	public abstract Connection loadDefaultDataSource();
	
	public CommonDataSourceService(Class<?> childClazz){
		System.out.println("new child created : " + childClazz.getName());
		this.childClazzs.add(childClazz);
	}
	private List<Class<?>> childClazzs = new ArrayList<Class<?>>();
	private Log log = LogFactory.getLog(CommonDataSourceService.class);
}
