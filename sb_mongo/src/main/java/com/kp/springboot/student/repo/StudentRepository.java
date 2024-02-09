package com.kp.springboot.student.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kp.springboot.student.model.Student;

public interface StudentRepository extends MongoRepository<Student, Integer>{
	
	public Student findByName(String name);

}
