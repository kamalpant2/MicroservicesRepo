package com.kp.springboot.student.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="students")
public class Student {

	private int id;
	private String name;
	private String college;
	private String city;
	private String companyname;
	
	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public Student(int id, String name, String college, String city,String companyname) {
		super();
		this.id = id;
		this.name = name;
		this.college = college;
		this.city = city;
		this.companyname=companyname;
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

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
}
