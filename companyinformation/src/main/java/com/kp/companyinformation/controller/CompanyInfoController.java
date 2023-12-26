package com.kp.companyinformation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyInfoController {

	@Autowired
	Environment env;
	@GetMapping("/checkinstance")
	public String Instancetest()
	{
		return "Instance running at port: "+env.getProperty("local.server.port");
	}
}
