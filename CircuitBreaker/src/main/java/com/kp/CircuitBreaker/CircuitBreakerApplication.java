package com.kp.CircuitBreaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker

public class CircuitBreakerApplication 
{
    public static void main(String[] args )
    {
        SpringApplication.run(CircuitBreakerApplication.class,args);
    }
    
    @Bean
	public RestTemplate template()
	{	
		return new RestTemplate();	
	}
}
