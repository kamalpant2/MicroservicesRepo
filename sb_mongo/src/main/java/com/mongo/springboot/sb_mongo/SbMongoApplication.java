package com.mongo.springboot.sb_mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class SbMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbMongoApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate()
	{
		RestTemplate resttemplate=new RestTemplate();
		return resttemplate;
	}

}

