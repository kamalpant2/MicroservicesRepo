package com.kp.CircuitBreaker.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.kp.CircuitBreaker.model.Student;
import com.kp.CircuitBreaker.service.CircuitBreakerService;

@RestController
@RequestMapping("/studentData")

public class CircuitBreakerController {

	
	@Autowired
	RestTemplate rt;
	
	@Autowired
	CircuitBreakerService circuitbreakerservice;
	
	@GetMapping("/getData1")
	public ResponseEntity<List<Student>> getData1()
	{
		ResponseEntity<List<Student>> re=circuitbreakerservice.apiResponse1();
		return re;
	}
	
	@GetMapping("/getData2")
	public ResponseEntity<List<Student>> getData2(){
		
		ResponseEntity<List<Student>> re=circuitbreakerservice.apiResponse2();	
		return re;
	}
	
	
	
	
	
	
	
	
}
