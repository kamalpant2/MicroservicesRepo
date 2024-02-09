package com.kp.Feignclient.Interface;


import java.util.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.kp.Feignclient.model.Student;

//url="http://localhost:8093",
@FeignClient(url="http://localhost:8093",name="feigns")
public interface FeignClientInterface {
	
        @GetMapping("/student/getStudents")
       public ResponseEntity<List<Student>> getStudents();
        
       @PostMapping("/student/saveStudent")
       public ResponseEntity<?> saveStudent(@RequestHeader("Authorization") String authHeader,Student s);
}
