package com.org;

import net.sf.json.JSONObject;

public interface Connection {
	public String getId();

	public JSONObject executeQuery(JSONObject requestJson);
	
}
