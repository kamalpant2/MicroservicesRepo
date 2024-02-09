package com.kp.springboot.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kp.springboot.student.dto.Company;
import com.kp.springboot.student.dto.StudentData;
import com.kp.springboot.student.model.Student;
import com.kp.springboot.student.repo.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentrepo;
	
	@Autowired
	RestTemplate resttemplate;
	
	@Value("${company.baseurl}")
	String baseurl;
    
	@Value("${company.endpoint}")
	String endpoint;
	
	public StudentData getStudentAndComany(String studentname) {
		
		Student student=studentrepo.findByName(studentname);
		System.out.println("student:"+student);
		if(student!=null )
		{
			if(student.getCompanyname()!=null)
			{
				System.out.println(student.getCompanyname());
				Company company=companyData(student.getCompanyname());
				//System.out.println("headoffice:"+company.getHeadoffice());
				return new StudentData(student,company);
			}
			return new StudentData(student,null);
		}
		return null;
	}
	
	public Company companyData(String companyname) 
	{
		String fullurl=baseurl+endpoint+companyname;
		Company company=resttemplate.getForObject(fullurl, Company.class);
		return company;
	}

}
