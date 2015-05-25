package com.org.services.busi;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.org.dao.CommonDao;
import com.org.util.MD5;
import com.org.util.SpringUtil;

/**
 * 通用
 * @author Administrator
 *
 */
@Service
public class CommemorateService {
	private final String getCommemorateById = "select * from kol_commemorate_board where channel_id = ? order by id desc";
	private final String getAllCommemorate = "select * from kol_commemorate_board order by id desc";
	private final String getLimitCommemorate = "select * from kol_commemorate_board a left join kol_files b on a.file_id = b.id order by a.id desc limit ?";
	
	/**
	 * 查询指定记录
	 * @param id
	 * @return
	 */
	public JSONArray getCommemorateById(String id){
		CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
		Map<Integer , Object> params = new HashMap<Integer, Object>();
		params.put(1, id);
		JSONArray testimonials = commonDao.queryJSONArray(getCommemorateById, params);
		return testimonials;
	}
	
	/**
	 * 查询所有记录, 但是，默认只查
	 * @param id
	 * @return
	 */
	public JSONArray getLimitCommemorate(String count){
		CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
		Map<Integer , Object> params = new HashMap<Integer, Object>();
		params.put(1, Integer.valueOf(count));
		JSONArray commemorate = commonDao.queryJSONArray(getLimitCommemorate, params);
		return commemorate;
	}
}
