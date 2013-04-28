package com.isis.android.general;

import java.io.Serializable;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Parameter implements Serializable{
	private String num;
	private String id;
	private String name;
	private String description;
	private List<String> choices;
	private String type="";
	
	
	
	
	public String getNum() {
		return num;
	}




	public void setNum(String num) {
		this.num = num;
	}




	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public List<String> getChoices() {
		return choices;
	}




	public void setChoices(List<String> choices) {
		this.choices = choices;
	}




	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}




	public View getView(Context context){
		View view =null;
			if(choices!=null){
				Spinner spinner = new Spinner(context);
				ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, android.R.id.text1);
				spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinner.setAdapter(spinnerAdapter);
				for (String s : choices) {
					spinnerAdapter.add(s);
				}				
				spinnerAdapter.notifyDataSetChanged();
				view = spinner;
			}else{
				view= DataConverter.convert(type, context);
			}
		
		return view;
	}
	
	
}
