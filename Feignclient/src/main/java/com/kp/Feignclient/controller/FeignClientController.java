package com.kp.Feignclient.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.kp.Feignclient.Interface.FeignClientInterface;
import com.kp.Feignclient.model.Student;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/feign")

public class FeignClientController {

	@Value("${spring.boot.security.user.name}")
	String username;
	
	@Value("${spring.boot.security.user.password}")
	String userpassword;
	
	@Value("${student.api}")
	String url;
	
	
	@Autowired
	private FeignClientInterface feigninter;
	
	@Autowired
	RestTemplate rt;
	
	@GetMapping("/getData")
	public ResponseEntity<?> getData()
	{	
		String credentialsString=HttpHeaders.encodeBasicAuth(username,userpassword,null);
		ResponseEntity<?> response=feigninter.getStudents();
		return response;
	}
	
	@PostMapping("/postData")
	public ResponseEntity<?> postData(@RequestBody Student std)
	{
		String credentialsString=HttpHeaders.encodeBasicAuth(username,userpassword,null);
		ResponseEntity<?> response=feigninter.saveStudent("Basic "+credentialsString,std);
		return response;
	}
	
  // need to create fallback for feign as well
	
	
}
