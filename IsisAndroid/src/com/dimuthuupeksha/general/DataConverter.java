package com.dimuthuupeksha.general;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

public class DataConverter {
	public static View convert(String type,Context context){
		
		if(type.equals("java.lang.Long")){
			return new EditText(context);
		}else if(type.equals("boolean")){
			return new CheckBox(context);
		}else if(type.equals("java.lang.Long")){
			return new EditText(context);
		}else if(type.equals("java.lang.String")){
			return new EditText(context);
		}else if(type.equals("org.joda.time.LocalDate")){
			return new DatePicker(context );
		}
		
		return null;
	}
}
