package com.org.services.busi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.org.dao.CommonDao;
import com.org.util.SpringUtil;

/**
 * Õ®”√
 * @author Administrator
 *
 */
@Service
public class ChannelService {
	private final static List<String> secretColumn = new ArrayList<String>();
	static {
		secretColumn.add("id");
	}
	
	/**
	 * @param loginName
	 * @return
	 */
	public JSONArray getTestimonialsByChannelId(String channelId){
		String sql = "select * from kol_testimonials where channel_id = ? order by id desc limit 100"; 
		CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
		Map<Integer , Object> params = new HashMap<Integer, Object>();
		params.put(1, channelId);
		JSONArray testimonials = commonDao.queryJSONArray(sql, params, secretColumn);
		return testimonials;
	}
	
	public JSONArray getTestimonialsByChannelId(String channelId, String limit){
		String sql = "select a.*, b.file_path from kol_testimonials a left join kol_testimonials_files b on a.file_id=b.id where channel_id = ? order by a.id desc limit ?";
		CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
		Map<Integer , Object> params = new HashMap<Integer, Object>();
		params.put(1, channelId);
		params.put(2, Integer.valueOf(limit));
		if(StringUtils.isEmpty(channelId)){
			params = new HashMap<Integer, Object>();
			sql = "select a.*, b.file_path from kol_testimonials a left join kol_testimonials_files b on a.file_id=b.id order by id desc limit ?";
			params.put(1, Integer.valueOf(limit));
		}
		JSONArray testimonials = commonDao.queryJSONArray(sql, params, secretColumn);
		return testimonials;
	}
	
	public JSONArray getTestimonialsByChannelId(String channelId, String limitFrom, String limitTo){
		String sql = "select * from kol_testimonials where channel_id = ? order by id desc"; 
		CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
		Map<Integer , Object> params = new HashMap<Integer, Object>();
		params.put(1, channelId);
		JSONArray testimonials = commonDao.queryJSONArray(sql, params, secretColumn);
		return testimonials;
	}
}
