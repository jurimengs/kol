package com.org.services.busi;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class TestimonialsService {
	private final String sql_insert = "insert into kol_testimonials (user_id, contents, create_date, update_date, channel_id, title, file_id) values (?,?,?,?,?,?,?)";
	private final String sql_getById = "select * from kol_testimonials where id = ?";
	private final static List<String> secretColumn = new ArrayList<String>();
	static {
		secretColumn.add("id");
	}
	
	public synchronized JSONObject saveContents(String userId, String contents, String channelId, String title, String fileId){
		String createDate = DateUtil.getDate(DateUtil.DATE_FORMAT_SHORT_DATE);
		
		CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
		Map<Integer , Object> params = new HashMap<Integer, Object>();
		params.put(1, Integer.valueOf(userId));
		params.put(2, contents);
		params.put(3, createDate);
		params.put(4, createDate);
		params.put(5, Integer.valueOf(channelId));
		params.put(6, title);
		params.put(7, fileId);
		
		JSONObject res = new JSONObject();
		try {
			commonDao.addSingle(sql_insert, params);
		} catch (SQLException e) {
			e.printStackTrace();
			res.put(CommonConstant.RESP_CODE, "DB00001");
			res.put(CommonConstant.RESP_MSG, "数据库保存异常");
		}
		return res;
	}
	
	public JSONObject getTestimonialById(String id){
		CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
		Map<Integer , Object> params = new HashMap<Integer, Object>();
		params.put(1, Integer.valueOf(id));
		JSONObject testimonial = new JSONObject();
		try {
			testimonial = commonDao.querySingle(sql_getById, params, secretColumn);
		} catch (SvcException e) {
			e.printStackTrace();
		}
		return testimonial;
	}
}
