package com.dimuthuupeksha.activity;

import com.dimuthuupeksha.general.Action;
import com.dimuthuupeksha.general.Service;
import com.example.isisandroid.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ActionViewActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		Service service = (Service)intent.getSerializableExtra("SERVICE");
		
		final Action []actions = service.getAllActions();
		if(actions!=null){
			String titles[] = new String[actions.length];
			for(int i=0;i<actions.length;i++){
				titles[i] = actions[i].getFriendlyName();
			}
			setListAdapter(new ArrayAdapter<String>(this, R.layout.slingle_list_item,titles)); 
			ListView list = getListView();
			list.setOnItemClickListener( new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					Intent intent = new Intent("android.intent.action.INVOKEVIEW");
					intent.putExtra("ACTION", actions[arg2]);
					startActivity(intent);
					
				}
				
			});
		}
		
		
		
	}
}
