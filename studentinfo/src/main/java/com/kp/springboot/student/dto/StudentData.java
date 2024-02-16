package com.kp.springboot.student.dto;

import com.kp.springboot.student.model.Student;

public class StudentData {

	

	private int id;
	private String name;
	private String college;
	private String city;
	private String companyname;
	private Company companyDetail;

	public StudentData(Student student,Company company) {
		super();
		this.id = student.getId();
		this.name = student.getName();
		this.college = student.getCollege();
		this.city = student.getCity();
		this.companyname=student.getCompanyname();
		this.companyDetail = company;
	}
	
	public StudentData() {
		
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public Company getCompanyDetail() {
		return companyDetail;
	}

	public void setCompanyDetail(Company companyDetail) {
		this.companyDetail = companyDetail;
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
	
	@Override
	public String toString() {
		return "StudentData [id=" + id + ", name=" + name + ", college=" + college + ", city=" + city + ", companyname="
				+ companyname + ", companyDetail=" + companyDetail + "]";
	}
	
}
