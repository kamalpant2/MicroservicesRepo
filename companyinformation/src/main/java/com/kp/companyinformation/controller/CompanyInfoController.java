package com.kp.companyinformation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.kp.companyinformation.model.Company;
import com.kp.companyinformation.repository.CompanyRepository;

@RestController
@RequestMapping("/company")
public class CompanyInfoController {

	@Autowired
	Environment env;
	
	@Autowired
	CompanyRepository comprepo;
	
	@GetMapping("/checkinstance")
	public String Instancetest()
	{
		return "Instance running at port: "+env.getProperty("local.server.port");
	}
	
	@GetMapping("/getcompany/{companyname}")
	ResponseEntity<Company> getCompany(@PathVariable String companyname)
	{
		Company re=comprepo.findByName(companyname);
		return ResponseEntity.ok(re);	
	}
	
	
}
