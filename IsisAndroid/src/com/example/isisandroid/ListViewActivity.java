package com.example.isisandroid;

import com.isis.android.general.Service;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewActivity extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		Object[] dene = (Object[])intent.getSerializableExtra("SERVICE_LIST");
		Service services[]= new Service[dene.length];
		String titles[] = new String[dene.length];
		for(int i=0;i<dene.length;i++){
			services[i]= (Service)dene[i];
			titles[i]= services[i].getTitle();
			Log.v("id ",services[i].getId());
		}
		 
		setListAdapter(new ArrayAdapter<String>(this, R.layout.slingle_list_item,titles)); 
		ListView list = getListView();
	}
}
