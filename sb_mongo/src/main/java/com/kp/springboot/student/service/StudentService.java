package com.kp.springboot.student.service;

import java.util.List;

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
	
	@Value("${company.checkinstance.endpoint}")
    String checkinstanceendpoint;
	
	public Student saveStudent(Student student) {
		Student savedStudent=this.studentrepo.save(student);
		return savedStudent;
	}
	
	public Student findStudent(int id) {
		Student student=this.studentrepo.findById(id).orElse(null);
		return student;
	}
	
	public void deleteStudent(int id) {
		this.studentrepo.deleteById(id);
	}
	
	public List<Student> findStudents(){
		List<Student> students=this.studentrepo.findAll();
		return students;
	}
	
	public StudentData getStudentAndComany(String studentname) {
		
		Student student=studentrepo.findByName(studentname);
		if(student!=null )
		{
			if(student.getCompanyname()!=null)
			{
				Company company=companyData(student.getCompanyname());
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
	
	public String getInstance() {
		String fullurl=baseurl+checkinstanceendpoint;
		String response=resttemplate.getForObject(fullurl, String.class);
		return response;
	}
	

}
