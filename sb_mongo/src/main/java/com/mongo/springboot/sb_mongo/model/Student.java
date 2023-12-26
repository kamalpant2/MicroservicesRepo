package com.mongo.springboot.sb_mongo.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="students")
public class Student {

	private int id;
	private String name;
	private String college;
	private String city;
	
	public Student(int id, String name, String college, String city) {
		super();
		this.id = id;
		this.name = name;
		this.college = college;
		this.city = city;
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
