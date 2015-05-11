package com.org.connection;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import org.springframework.util.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.org.Connection;
import com.org.common.CommonConstant;
import com.org.utils.JSONUtils;

public class MongoConnection implements Connection<Void> {

	@Override
	public String getId() {
		return CommonConstant.DB_MONGO;
	}
	
	public JSONObject queryList(Map<Object, Object> param, String collectionName) {
		JSONArray list = new JSONArray();
		// 排序字段
		String sortBy = null;
		if(param.containsKey("sortBy")){
			sortBy = param.get("sortBy").toString();
			param.remove("sortBy");
		}
		
		// 升序降序
		Integer sortSerial = CommonConstant.ASC;
		if(param.containsKey("sortSerial")){
			String _sortSerial = param.get("sortSerial").toString();
			sortSerial = Integer.valueOf(_sortSerial);
			param.remove("sortSerial");
		}
		// 单页记录数
		Integer pageCounts = 20;
		if(param.containsKey("pageCounts")){
			String _pageCounts = param.get("pageCounts").toString();
			pageCounts = Integer.valueOf(_pageCounts);
			param.remove("pageCounts");
		}

		// 页码
		Integer currentPage = 0;
		if(param.containsKey("currentPage")){
			String _currentPage = param.get("currentPage").toString();
			currentPage = Integer.valueOf(_currentPage);
			param.remove("currentPage");
		}
		
		DBObject query = new BasicDBObject();
		if(param != null){
			query.putAll(param);
		}
		
		DBCursor cursor = null;
		DBObject sort = new BasicDBObject(sortBy, sortSerial);
		Integer skip = currentPage * pageCounts;
		
		cursor = mongo.getDB(databaseName).getCollection(collectionName).find(query)
				.sort(sort)
				.skip(skip)
				.limit(pageCounts);
		
		if(cursor != null){
			list = JSONUtils.getJsonArrayFromCollection(cursor.toArray());
		}
		
		JSONObject totalObj = getTotalObject(collectionName, query, pageCounts, currentPage);
		
		JSONObject res = new JSONObject();
		res.put("record", list);
		res.put("totalObj", totalObj);
		return res;
	}

	private JSONObject getTotalObject(String collectionName, DBObject query, Integer pageCounts, Integer currentPage) {

		DBCursor total = mongo.getDB(databaseName).getCollection(collectionName).find(query);
		
		JSONObject totalObj = new JSONObject();
		totalObj.put("totalCounts", total.count());
		
		Integer pages = 0;
		if(total.count() > 0){
			if(total.count() <= pageCounts){
				// 总数小于单页记录数
				pages = 1;
			} else {
				pages = Math.round(total.count() / pageCounts);
				if(total.count() != pages * pageCounts){
					pages = pages + 1;
				}
			}
		}
		
		totalObj.put("totalPageCounts", pages);
		totalObj.put("currentPage", currentPage);
		return totalObj;
	}

	@Override
	public JSONObject executeQuery(JSONObject requestJson) {
		//String queryFlag = requestJson.getString("queryFlag");
		String collectionName = requestJson.getString("collectionName");
		
		Map<Object, Object> queryParams = JSONUtils.parseJSON2Map(requestJson.getJSONObject("queryParams"));

		JSONObject res = queryList(queryParams, collectionName);

		JSONObject totalObj = (JSONObject)res.remove("totalObj");
		JSONArray record = (JSONArray)res.remove("record");
		
		JSONObject result = new JSONObject();
		if(totalObj.containsKey("currentPage")){
			result.put("currentPage", totalObj.getString("currentPage"));
		} else {
			result.put("currentPage", "0");
		}
		result.put("totalCounts", totalObj.getString("totalCounts"));
		result.put("totalPageCounts", totalObj.getString("totalPageCounts"));
		result.put("memo1", "");
		result.put("memo2", "");
		result.put(CommonConstant.RESP_CODE, "10000");
		result.put(CommonConstant.RESP_MSG, "操作成功");
		result.put("busiInfo", record);
		
		return result;
	}

	public MongoConnection(String host, int port, String databaseName) throws UnknownHostException {
//		MongoOptions op = new MongoOptions();
//		op.setConnectTimeout(timeoutMS);
//		Mongo mongo = new Mongo(host, op);
		
		Mongo mongo = null;
		mongo = new Mongo(new ServerAddress(host, port));
		//mongo.slaveOk(); 弃用
		mongo.setReadPreference(ReadPreference.secondaryPreferred());// 推荐
		this.mongo = mongo;
		this.databaseName = databaseName;
	}
	
	public MongoConnection(Mongo mongo, String databaseName) {
		//mongo.slaveOk();
		mongo.setReadPreference(ReadPreference.secondaryPreferred());
		this.mongo = mongo;
		this.databaseName = databaseName;
	}
	
	public MongoConnection(){}
	
	private Mongo mongo;
	private String databaseName;
	@Override
	public Void getRealConnection() {
		// TODO mongo数据源应该用不上这个
		return null;
	}
	
	public void close(Void obj) {
	
	}
}
