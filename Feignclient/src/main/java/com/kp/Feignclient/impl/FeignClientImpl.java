package com.kp.Feignclient.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.kp.Feignclient.Interface.FeignClientInterface;
import com.kp.Feignclient.model.Student;

@Component
public class FeignClientImpl implements FeignClientInterface {

	
	@SuppressWarnings("unchecked")
	public ResponseEntity<List<Student>> getStudents() {
		
		return (ResponseEntity<List<Student>>) Collections.EMPTY_LIST;
	}

}
