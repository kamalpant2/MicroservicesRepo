package com.mongo.springboot.sb_mongo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongo.springboot.sb_mongo.model.Student;

public interface StudentRepository extends MongoRepository<Student, Integer>{

}
