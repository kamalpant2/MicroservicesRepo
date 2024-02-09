package com.kp.springboot.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.kp.springboot.student.dto.StudentData;
import com.kp.springboot.student.model.Student;
import com.kp.springboot.student.repo.StudentRepository;
import com.kp.springboot.student.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentRepository studentrepo;
	
    @Autowired
	RestTemplate resttemplate;
    
    @Autowired
    StudentService studentservice;
    
    @Value("${company.baseurl}")
    String baseurl;
    
    @Value("${company.checkinstance.endpoint}")
    String checkinstanceendpoint;
	
	@PostMapping("/saveStudent")
	ResponseEntity<?> addStudent(@RequestBody Student student)
	{
		try{
			Student savedStudent=this.studentrepo.save(student);
			if(null!=savedStudent)
			{
				return ResponseEntity.ok(savedStudent);
			}	
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Record not Saved");
		   }
		catch(DataAccessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("DB Socket Open Exception");
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Record not Saved due to internal server error");
		}
	}
	
	@DeleteMapping("/deleteStudent/{id}")
	ResponseEntity<?> deleteStudent(@PathVariable int id)
	{
		try 
		{
			if(null!=studentrepo.findById(id).orElse(null))
			{
			  this.studentrepo.deleteById(id);
			  return ResponseEntity.ok("Record deleted");
			}
			else
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Record Found");
			
		}
		catch(DataAccessException e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("DB Socket Open Exception");
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Record not deleted due to internal server error");
		}
	}
	
	@GetMapping("/getStudents")
	ResponseEntity<?> getStudents()
	{
		try{
			List<Student> getStudents=this.studentrepo.findAll();
			if(null!=getStudents)
			{
				return ResponseEntity.ok(getStudents);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Record Found");
		}
		catch(DataAccessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("DB Socket Open Exception");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
		}
		
	}
	
	
	@GetMapping("/getStudent/{id}")
	ResponseEntity<?> getStudent(@PathVariable int id)
	{
		try {
		Student student=this.studentrepo.findById(id).orElse(null);
		if(null!=student)
		{
			return ResponseEntity.ok(student);		
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record Not Found");	
		}
		catch(DataAccessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("DB Socket Open Exception");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
		}
	}
	
	//fetching student data from student ms and company details from company ms
	@GetMapping("/getStudentData/{studentname}")
	public ResponseEntity<?> getStudentData(@PathVariable String studentname)
	{
		try
		{
		StudentData studentdata=studentservice.getStudentAndComany(studentname);
		if(null!=studentdata)
		{
			return ResponseEntity.ok(studentdata);
		}
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record Not Found");
		}
		catch(DataAccessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("DB Socket Open Exception");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error pls check internally called service too");
		}
	}
	
	//api for checking different instances of company service
	@GetMapping("/checkcompanyinstance")
	public String getInstance()
	{
		String fullurl=baseurl+checkinstanceendpoint;
		String response=resttemplate.getForObject(fullurl, String.class);
		return response;
	}
}
