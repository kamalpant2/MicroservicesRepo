package com.kp.Feignclient.Interface;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.kp.Feignclient.impl.FeignClientImpl;
import com.kp.Feignclient.model.Student;

//url="http://localhost:8093",
@FeignClient(url="http://localhost:8093",fallback=FeignClientImpl.class,name="feigns")
public interface FeignClientInterface {
	
        @GetMapping("/student/getStudents")
       public ResponseEntity<List<Student>> getStudents();
        
}
