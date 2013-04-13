package com.isis.android.general;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Service implements Serializable{
	private String id;
	private String rel;
	private String href;
	private String method;
	private String type;
	private String title;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Service(String id, String rel, String href, String method,
			String type, String title) {
		super();
		this.id = id;
		this.rel = rel;
		this.href = href;
		this.method = method;
		this.type = type;
		this.title = title;
	}
	
	public Service(){
		
	}
	
	
}
