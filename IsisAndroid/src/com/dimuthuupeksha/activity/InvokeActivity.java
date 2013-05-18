package com.dimuthuupeksha.activity;


import com.dimuthuupeksha.general.Action;
import com.dimuthuupeksha.general.InvokeResult;
import com.dimuthuupeksha.general.Parameter;
import com.dimuthuupeksha.general.Service;
import com.example.isisandroid.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
		if(parameters.length>0){
			for(int i=0;i<parameters.length;i++){
				TextView tv = new TextView(this);
				tv.setText(parameters[i].getName());
				layout.addView(tv);
				View view = parameters[i].getView(this);
				if(view!=null)
				layout.addView(view);
			}
		}else{
			InvokeResult res =  action.invokeAction(null);
			Log.v("type", res.getResultType());
			Intent intent2 = new Intent("android.intent.action.RESULTVIEW");
			intent2.putExtra("TEST", "hooooo");
			intent2.putExtra("RESULT", res);
			startActivity(intent2);
		}
        
		
	}
}
