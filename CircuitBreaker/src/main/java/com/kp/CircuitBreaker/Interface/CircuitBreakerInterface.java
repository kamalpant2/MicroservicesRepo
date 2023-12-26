package com.kp.CircuitBreaker.Interface;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.kp.CircuitBreaker.impl.CircuitBreakerImpl;
import com.kp.CircuitBreaker.model.Student;

//url="http://localhost:8093",
@FeignClient(url="http://localhost:8093",fallback=CircuitBreakerImpl.class,name="feigns")
public interface CircuitBreakerInterface {
	
        @GetMapping("/student/getStudents")
       public ResponseEntity<List<Student>> getStudents();
        
}
