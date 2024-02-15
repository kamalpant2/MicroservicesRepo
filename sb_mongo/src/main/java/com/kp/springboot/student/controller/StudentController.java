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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.kp.springboot.student.dto.StudentData;
import com.kp.springboot.student.model.Student;
import com.kp.springboot.student.service.StudentService;

import jdk.nashorn.internal.runtime.logging.Logger;

@RestController
@RequestMapping("/student")

public class StudentController {

	@Autowired
	RestTemplate resttemplate;

	@Autowired
	StudentService studentservice;


	@PostMapping("/saveStudent")
	public ResponseEntity<?> addStudent(@RequestBody Student student)
	{
		try{

			if(null==studentservice.findStudent(student.getId())) 
			{
				Student savedStudent=studentservice.saveStudent(student);
				if(null!=savedStudent)
				{
					return ResponseEntity.ok("Student saved Successfully.");
				}	
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Record not Saved");
			}
			else
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Record with same id already exists");
		}
		catch(DataAccessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to connect to DB");
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Record not Saved due to internal server error");
		}
	}

	@DeleteMapping("/deleteStudent/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable int id)
	{
		try 
		{
			if(null!=studentservice.findStudent(id))
			{
				studentservice.deleteStudent(id);
				return ResponseEntity.ok("Record deleted");
			}
			else
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Record Found");

		}
		catch(DataAccessException e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to connect to DB");
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Record not deleted due to internal server error");
		}
	}

	@GetMapping("/getStudents")
	public ResponseEntity<?> getStudents()
	{
		try{
			List<Student> students=studentservice.findStudents();		
			if(null!=students)
			{
				return ResponseEntity.ok(students);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Record Found");
		}
		catch(DataAccessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to connect to DB");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
		}

	}


	@GetMapping("/getStudent/{id}")
	public ResponseEntity<?> getStudent(@PathVariable int id)
	{
		try {
			Student student=studentservice.findStudent(id);

			if(null!=student)
			{
				return ResponseEntity.ok(student);	
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record Not Found");	
		}
		catch(DataAccessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to connect to DB");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
		}
	}

	//fetching student data from student ms and company details from company ms
	@GetMapping("/getStudentDetail/{studentname}")
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
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to connect to DB");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error pls check internally called service too");
		}
	}


	@PutMapping("/updateStudent")
	public ResponseEntity<?> updateStudent(@RequestBody Student student)
	{
		try 
		{
			if(null!=studentservice.findStudent(student.getId()))
			{
				Student updatedstudent=studentservice.saveStudent(student);
				if(null!=updatedstudent)
					return ResponseEntity.ok(updatedstudent);
				else
					return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Record could not be updated");	
			}
			else
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This record does not exists hence can't be updated");	
		}
		catch(DataAccessException e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to connect to DB");
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Record not deleted due to internal server error");
		}
	}

	//api for checking different instances of company service
	@GetMapping("/checkcompanyinstance")
	public String getInstance()
	{
		return studentservice.getInstance();
	}
}
