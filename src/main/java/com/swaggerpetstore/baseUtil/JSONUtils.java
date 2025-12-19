package com.swaggerpetstore.baseUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtils {
	public static boolean isJSONValid(String test) {
		try {
			new JSONObject(test);
		} catch (JSONException e) {
			try {
				new JSONArray(test);
			} catch (JSONException ex) {
				return false;
			}
		}
		return true;
	}
}
