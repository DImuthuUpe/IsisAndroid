package com.isis.android.general;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;



public class InvokeResult implements Serializable{
	private String totalHeader;
	
	public String getResultType(){
		Log.v("called", "yes");
		try {
			return getTotalHeader().getString("resulttype");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
	public HashMap<String, String>[] getValues(){
		try {
			JSONArray valueArr = getTotalHeader().getJSONObject("result").getJSONArray("value");
			HashMap<String, String> valMaps[] = new HashMap[valueArr.length()];
			for(int i=0;i<valueArr.length();i++){
				JSONObject singleVal = valueArr.getJSONObject(i);
				Iterator<String> it = singleVal.keys();
				HashMap<String, String> res = new HashMap<String, String>();
				while (it.hasNext()) {
					String key =it.next();
					res.put(key, singleVal.getString(key));					
				}
				valMaps[i] = res;
			}
			return valMaps;
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getValue(){
		
		try {
			return getTotalHeader().getJSONObject("result").getString("value");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public JSONObject getTotalHeader() {
		JSONObject obj;
		try {
			obj = new JSONObject(totalHeader);
			return obj;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setTotalHeader(JSONObject totalHeader) {
		this.totalHeader = totalHeader.toString();
	}
	
	
}
