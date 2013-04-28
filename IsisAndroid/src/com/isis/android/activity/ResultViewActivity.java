package com.isis.android.activity;

import com.example.isisandroid.R;
import com.isis.android.general.InvokeResult;
import com.isis.android.general.Service;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ResultViewActivity extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		InvokeResult res = (InvokeResult)intent.getSerializableExtra("RESULT");
		
		if(res.getResultType().equals("list")){
			String titles[] = new String[res.getValues().length];
			for(int i=0;i<res.getValues().length;i++){
				titles[i]= res.getValues()[i].get("title");
				
			}			 
			setListAdapter(new ArrayAdapter<String>(this, R.layout.slingle_list_item,titles)); 
			ListView list = getListView();
		}else if(res.getResultType().equals("scalarvalue")){
			String titles[] = new String[1];
			
			titles[0]= res.getValue();
				
					 
			setListAdapter(new ArrayAdapter<String>(this, R.layout.slingle_list_item,titles)); 
			ListView list = getListView();
		}
	}
}
