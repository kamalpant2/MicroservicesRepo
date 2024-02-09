package com.kp.companyinformation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kp.companyinformation.model.Company;

public interface CompanyRepository extends MongoRepository<Company,Integer>{

	public Company findByName(String companyname);

}
