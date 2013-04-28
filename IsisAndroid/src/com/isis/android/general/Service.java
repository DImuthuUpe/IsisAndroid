package com.isis.android.general;

import java.io.Serializable;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.isis.android.json.JSONParser;

@SuppressWarnings("serial")
public class Service implements Serializable{
	private String id;
	private String rel;
	private String href;
	private String method;
	private String type;
	private String title;
	private String uname;
	private String pass;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Service(String id, String rel, String href, String method,
			String type, String title,String uname,String pass) {
		super();
		this.id = id;
		this.rel = rel;
		this.href = href;
		this.method = method;
		this.type = type;
		this.title = title;
		this.uname = uname;
		this.pass = pass;
	}
	
	public Service(String uname,String pass){
		this.uname = uname;
		this.pass = pass;
	}
	
	public Action[] getAllActions(){
		JSONParser jp = new JSONParser();
		JSONObject obj=  jp.getJSONFromUrl(href,uname,pass);
		Action actions[] = null;
		try {
			JSONArray actionarray = obj.getJSONArray("members");
			actions= new Action[actionarray.length()];
			for(int i=0;i<actionarray.length();i++){
				if(actionarray.getJSONObject(i).getString("memberType").equals("action")){
					Action a = new Action(uname,pass);
					a.setId(actionarray.getJSONObject(i).getString("id"));
					a.setMemberType(actionarray.getJSONObject(i).getString("memberType"));
					JSONArray links = actionarray.getJSONObject(i).getJSONArray("links");
					HashMap< String, String> linkMap = new HashMap<String, String>();
					linkMap.put("rel", links.getJSONObject(0).getString("rel"));
					linkMap.put("href", links.getJSONObject(0).getString("href"));
					linkMap.put("method", links.getJSONObject(0).getString("method"));
					linkMap.put("type", links.getJSONObject(0).getString("type"));
					a.setLink(linkMap);
					actions[i] =a;
				}
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actions;
	}
	
}
