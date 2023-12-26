package com.mongo.springboot.sb_mongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mongo.springboot.sb_mongo.model.Student;
import com.mongo.springboot.sb_mongo.repo.StudentRepository;

@RestController
@RequestMapping("/student")
public class MyController {

	@Autowired
	private StudentRepository studentrepo;
	
    @Autowired
	RestTemplate resttemplate;
	
	@PostMapping("/saveStudent")
	ResponseEntity<?> addStudent(@RequestBody Student student)
	{
		Student savedStudent=this.studentrepo.save(student);
		if(null==savedStudent)
		{
			return ResponseEntity.ok("Record Not Saved");
		}
		return ResponseEntity.ok(savedStudent);
		
	}
	
	@GetMapping("/getStudents")
	ResponseEntity<?> getStudents()
	{
		List<Student> getStudents=this.studentrepo.findAll();
		if(null==getStudents)
		{
			return ResponseEntity.ok("No Record Found");
			//return (ResponseEntity<?>) ResponseEntity.noContent();
		}
		return ResponseEntity.ok(getStudents);
	}
	
	
	@GetMapping("/getStudent/{id}")
	ResponseEntity<?> getStudent(@PathVariable int id)
	{
		Student getStudent=this.studentrepo.findById(id).orElse(null);
		if(null==getStudent)
		{
			return ResponseEntity.ok("Record Not Found");
			
		}
		return ResponseEntity.ok(getStudent);
	}
	
	@GetMapping("/testcompany")
	public String getCompany()
	{
		String url="http://localhost:8095/company/checkinstance";
		String response=resttemplate.getForObject(url, String.class);
		return response;
	}
}
