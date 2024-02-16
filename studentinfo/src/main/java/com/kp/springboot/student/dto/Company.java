package com.kp.springboot.student.dto;

public class Company {
	String name;
	String headoffice;
	
	public Company() {
		super();
	}
	public Company(String name, String headoffice) {
		super();
		this.name = name;
		this.headoffice = headoffice;
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
	
	@Override
	public String toString() {
		return "Company [name=" + name + ", headoffice=" + headoffice + "]";
	}

}
