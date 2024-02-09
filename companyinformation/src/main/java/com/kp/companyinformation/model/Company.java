package com.kp.companyinformation.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Company")
public class Company {
	
	int id;
	String name;
	String headoffice;
	
	public Company() {
		super();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeadoffice() {
		return headoffice;
	}
	public void setHeadoffice(String headoffice) {
		this.headoffice = headoffice;
	}
	
	

}
