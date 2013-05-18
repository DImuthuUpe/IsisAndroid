package com.dimuthuupeksha.general;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.dimuthuupeksha.json.JSONParser;

public class Action implements Serializable{

	private String id;
	private String memberType;
	private HashMap<String, String> link;
	private String uname;
	private String pass;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public HashMap<String, String> getLink() {
		return link;
	}
	public void setLink(HashMap<String, String> link) {
		this.link = link;
	}
	
	public Action(String uname,String pass){
		this.uname = uname;
		this.pass= pass;
	}
	
	public String getFriendlyName(){
		JSONParser jp = new JSONParser();
		JSONObject obj=  jp.getJSONFromUrl(link.get("href"),uname,pass);
		String description = "";
		try {
			JSONArray arr = obj.getJSONArray("links");
			for(int i=0;i<arr.length();i++){
				if(arr.getJSONObject(i).getString("rel").equals("describedby")){
					String deshref = arr.getJSONObject(i).getString("href");
					obj=  jp.getJSONFromUrl(deshref,uname,pass);
					description = obj.getJSONObject("extensions").getString("friendlyName");
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return description;
	}
	
	public Parameter[] getParameters(){
		JSONParser jp = new JSONParser();
		JSONObject obj=  jp.getJSONFromUrl(link.get("href"),uname,pass);
		try {
			JSONArray params = obj.getJSONArray("parameters");
			Parameter[] actionparams = new Parameter[params.length()];
			for(int i=0;i<params.length();i++){
				Parameter p = new Parameter();
				p.setNum(params.getJSONObject(i).getString("num"));
				p.setId(params.getJSONObject(i).getString("id"));
				p.setName(params.getJSONObject(i).getString("name"));
				p.setDescription(params.getJSONObject(i).getString("description"));
				if(params.getJSONObject(i).has("choices")){
					JSONArray choices =  params.getJSONObject(i).getJSONArray("choices");
					List<String> choiceslist =  new ArrayList<String>();
					for(int j=0;j<choices.length();j++){
						choiceslist.add(choices.getString(j));
					}
					p.setChoices(choiceslist);
				}
				actionparams[i] = p;
				
			}
			params = obj.getJSONArray("links");
			JSONObject desc=getJSONObjectByName(params, "rel", "describedby");
			if(desc!=null){
				String href = desc.getString("href");
				JSONObject actionDesc = jp.getJSONFromUrl(href, uname, pass);
				JSONArray actionDescParams = actionDesc.getJSONArray("parameters");
				for(int i=0;i<actionparams.length;i++){
					JSONObject param = getJSONObjectByName(actionDescParams, "id", id+"-"+actionparams[i].getName());
					if(param!=null){
						String href2 = param.getString("href");
						JSONObject parmDesc = jp.getJSONFromUrl(href2, uname, pass);
						JSONArray paramLinks = parmDesc.getJSONArray("links");
						JSONObject returnType = getJSONObjectByName(paramLinks, "rel", "returntype");
						if(returnType!= null){
							String href3 = returnType.getString("href");
							JSONObject dataType = jp.getJSONFromUrl(href3, uname, pass);
							if(dataType.has("canonicalName")){
								actionparams[i].setType(dataType.getString("canonicalName"));
							}
						}
					}
				}
			
			}
			
			return actionparams;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	private JSONObject getJSONObjectByName(JSONArray arr, String name,String value){
		
		for(int i=0;i<arr.length();i++){
			try {
				if(arr.getJSONObject(i).has(name)){
					if(arr.getJSONObject(i).get(name).equals(value)){
						return arr.getJSONObject(i);
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	public InvokeResult invokeAction(Map<String, String> args){
		JSONParser jp = new JSONParser();
		JSONObject obj=  jp.getJSONFromUrl(link.get("href"),uname,pass);
		try {
			JSONArray params = obj.getJSONArray("links");
			JSONObject inv=getJSONObjectByName(params, "rel", "invoke");
			if(inv!=null){
				String method = inv.getString("method");
				String url = inv.getString("href");
				InvokeResult res = new InvokeResult();
				Log.v("method", method);
				Log.v("url", url);
				res.setTotalHeader(jp.getJSONFromUrl(url,uname,pass,method,args));
				return res;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
