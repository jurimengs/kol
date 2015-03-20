package com.org.services.busi;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.org.common.CommonConstant;
import com.org.dao.CommonDao;
import com.org.exception.SvcException;
import com.org.util.DateUtil;
import com.org.util.SpringUtil;

/**
 * @author Administrator
 *
 */
@Service
public class CommentsService {
	public synchronized JSONObject saveComments(String testimonialsId, String commentContent, String userId){
		String createDate = DateUtil.getDate(DateUtil.DATE_FORMAT_SHORT_DATE);
		
		String sql = "insert into kol_comment (testimonials_id, user_id, contents, create_date, update_date) values (?,?,?,?,?)"; 
		CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
		Map<Integer , Object> params = new HashMap<Integer, Object>();
		params.put(1, Integer.valueOf(testimonialsId));
		params.put(2, Integer.valueOf(userId));
		params.put(3, commentContent);
		params.put(4, createDate);
		params.put(5, createDate);
		
		JSONObject res = new JSONObject();
		try {
			commonDao.addSingle(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			res.put(CommonConstant.RESP_CODE, "DB00001");
			res.put(CommonConstant.RESP_MSG, "���ݿⱣ���쳣");
		}
		return res;
	}
	
	public JSONObject getCommentsByTesTimonialId(String id){
		
		TestimonialsService ts = (TestimonialsService) SpringUtil.getBean("testimonialsService");
		
		JSONObject res = new JSONObject();
		JSONObject tesTimonial = ts.getTestimonialById(id);
		res.put("tesTimonial", tesTimonial);

		JSONArray commentsArray = new JSONArray();
		
		String sql = "select * from kol_comment where testimonials_id = ?";
		CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
		Map<Integer , Object> params = new HashMap<Integer, Object>();
		params.put(1, Integer.valueOf(id));
		commentsArray = commonDao.queryJSONArray(sql, params);
		res.put("commentsArray", commentsArray);
		return res;
	}
}