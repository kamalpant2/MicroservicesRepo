package com.kp.Feignclient.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.kp.Feignclient.Interface.FeignClientInterface;
import com.kp.Feignclient.model.Student;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/feign")

public class FeignClientController {

	@Autowired
	private FeignClientInterface feigninter;
	
	@Autowired
	RestTemplate rt;
	
	@GetMapping("/getData")
	public ResponseEntity<?> getData()
	{
		ResponseEntity<?> response=feigninter.getStudents();
		return response;
	}
	
	@GetMapping("/getData2")
	@CircuitBreaker(name = "studentin",fallbackMethod="getDataFallback")
	public ResponseEntity<List<Student>> getData2(){
		ResponseEntity<List<Student>> re=rt.exchange("http://localhost:8093/student/getStudents",HttpMethod.GET,null,new ParameterizedTypeReference<List<Student>>(){});
		return re;
	}
	
	
	public ResponseEntity<String> getDataFallback(Exception e)
	{
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("service is not availble.");
	}
	
	@Bean
	public RestTemplate template()
	{	
		return new RestTemplate();	
	}
	
	
	
	
}
