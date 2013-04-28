package com.isis.android.activity;

import com.example.isisandroid.R;
import com.isis.android.general.Service;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ServiceViewActivity extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		Object[] dene = (Object[])intent.getSerializableExtra("SERVICE_LIST");


		final String url = intent.getExtras().getString("url");
		final String uname = intent.getExtras().getString("uname");
		final String pass = intent.getExtras().getString("pass");
		
		final Service services[]= new Service[dene.length];
		String titles[] = new String[dene.length];
		for(int i=0;i<dene.length;i++){
			services[i]= (Service)dene[i];
			titles[i]= services[i].getTitle();
			Log.v("id ",services[i].getId());
		}
		 
		setListAdapter(new ArrayAdapter<String>(this, R.layout.slingle_list_item,titles)); 
		ListView list = getListView();
		list.setOnItemClickListener( new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), arg2+" Selected" , Toast.LENGTH_SHORT).show();
				Intent intent = new Intent("android.intent.action.ACTIONVIEW");
				intent.putExtra("SERVICE", services[arg2]);		
				intent.putExtra("uname", uname);
				intent.putExtra("pass", pass);
				intent.putExtra("url", url);
				startActivity(intent);				
			}
			
		});
	}
}
