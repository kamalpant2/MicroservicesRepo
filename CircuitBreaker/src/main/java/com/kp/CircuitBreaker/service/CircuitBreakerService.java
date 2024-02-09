package com.kp.CircuitBreaker.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.kp.CircuitBreaker.model.Student;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class CircuitBreakerService {

	@Autowired
	RestTemplate rt;
	
	@Value("${spring.boot.security.user.name}")
	String username;
	
	@Value("${spring.boot.security.user.password}")
	String userpassword;
	
	@Value("${student.api}")
	String url;
	
	@CircuitBreaker(name = "studentin",fallbackMethod="getDataFallback2")
	public ResponseEntity<List<Student>> apiResponse2()
	{
		
		HttpHeaders headers=new HttpHeaders();
		headers.setBasicAuth(username,userpassword);
		headers.setContentType(MediaType.APPLICATION_JSON);
		RequestEntity<Void> re;
		try {
			re = new RequestEntity<Void>(headers,HttpMethod.GET,new URI(url));
			return rt.exchange(re,new ParameterizedTypeReference<List<Student>>(){});
		} 
		catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@HystrixCommand(fallbackMethod="getDataFallback1")
	public ResponseEntity<List<Student>> apiResponse1(){
		HttpHeaders headers=new HttpHeaders();
		headers.setBasicAuth(username,userpassword);
		headers.setContentType(MediaType.APPLICATION_JSON);
		RequestEntity<Void> re;
		try {
			re = new RequestEntity<Void>(headers,HttpMethod.GET,new URI(url));
			return rt.exchange(re,new ParameterizedTypeReference<List<Student>>(){});
		} 
		catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ResponseEntity<String> getDataFallback2(Exception e)
	{
		if(e instanceof ResourceAccessException)
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("service internally called is not availble.");
		if(e instanceof HttpClientErrorException.Unauthorized) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("user credentials are not valid");
		}
		return null;
	}
	
	public ResponseEntity<List<Student>> getDataFallback1()
	{
		List<Student> al=new ArrayList<>(Arrays.asList(new Student(0,"no student","no college","no city")));
		return ResponseEntity.ok(al);
	}
	
}
