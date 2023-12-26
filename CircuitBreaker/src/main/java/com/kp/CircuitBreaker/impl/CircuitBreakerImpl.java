package com.kp.CircuitBreaker.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.kp.CircuitBreaker.Interface.CircuitBreakerInterface;
import com.kp.CircuitBreaker.model.Student;

@Component
public class CircuitBreakerImpl implements CircuitBreakerInterface {

	
	@SuppressWarnings("unchecked")
	public ResponseEntity<List<Student>> getStudents() {
		
		return (ResponseEntity<List<Student>>) Collections.EMPTY_LIST;
	}

}
