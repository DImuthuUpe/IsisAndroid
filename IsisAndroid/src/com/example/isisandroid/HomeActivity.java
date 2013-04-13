package com.example.isisandroid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.isis.android.general.Service;
import com.isis.android.json.JSONParser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.System;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class HomeActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		Bundle extras = getIntent().getExtras();
		String url = extras.getString("url");
		String uname = extras.getString("uname");
		String pass = extras.getString("pass");
		TextView test = (TextView)findViewById(R.id.test);
		test.setText(url);
		
		JSONParser jp = new JSONParser();
		JSONObject obj=  jp.getJSONFromUrl(url,uname,pass);
		try {
			JSONArray linkarray = obj.getJSONArray("links");
			 Log.v("out","got links");
			for(int i=0;i<linkarray.length();i++){
				if(linkarray.getJSONObject(i).getString("rel").equals("services")){
					jp = new JSONParser();
					String serviceurl = linkarray.getJSONObject(i).getString("href");
					
					Log.v("service url",serviceurl);
					JSONObject serviceObj= jp.getJSONFromUrl(serviceurl,uname,pass);
					
					JSONArray serviJsonArray = serviceObj.getJSONArray("value");
					Log.v("size ",serviJsonArray.length()+"");
					if(serviJsonArray.length()>0){
						Service[] services = new Service[serviJsonArray.length()];
						for(int j=0;j<serviJsonArray.length();j++){
							String id = serviJsonArray.getJSONObject(j).getString("id");
							String rel = serviJsonArray.getJSONObject(j).getString("rel");
							String href = serviJsonArray.getJSONObject(j).getString("href");
							String method = serviJsonArray.getJSONObject(j).getString("method");
							String type = serviJsonArray.getJSONObject(j).getString("type");
							String title = serviJsonArray.getJSONObject(j).getString("title");
							
							services[j]= new Service(id, rel, href, method, type, title);
						}
						Intent intent = new Intent("android.intent.action.LISTVIEW");
						intent.putExtra("SERVICE_LIST", services);
						
						startActivity(intent);
						
					}
					
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.v("ddddd", "sdasf");
	    
	}
	

}
