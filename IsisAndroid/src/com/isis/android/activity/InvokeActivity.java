package com.isis.android.activity;


import com.example.isisandroid.R;
import com.isis.android.general.Action;
import com.isis.android.general.Parameter;
import com.isis.android.general.Service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class InvokeActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		Action action = (Action)intent.getSerializableExtra("ACTION");
		setContentView(R.layout.invoke);
		LinearLayout layout = (LinearLayout)findViewById(R.id.base);
		
		
		Parameter[] parameters = action.getParameters();
		for(int i=0;i<parameters.length;i++){
			TextView tv = new TextView(this);
			tv.setText(parameters[i].getName());
			layout.addView(tv);
			View view = parameters[i].getView(this);
			if(view!=null)
			layout.addView(view);
		}
        
		
	}
}
