package com.kp.Feignclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
@EnableFeignClients


public class FeignClientApplication 
{
    public static void main(String[] args )
    {
        SpringApplication.run(FeignClientApplication.class,args);
    }
    
    @Bean
	public RestTemplate template()
	{	
		return new RestTemplate();	
	}
}
