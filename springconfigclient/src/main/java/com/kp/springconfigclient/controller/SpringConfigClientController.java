package com.kp.springconfigclient.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.kp.springconfigclient.config.CloudConfig;

@RestController
@RefreshScope
@RequestMapping("/config")
public class SpringConfigClientController {
	
	@Value("${myservice.maximum}")
	private int max;
	
	@Autowired
	private Environment env;
	
//	@Autowired
//	private CloudConfig cloudconfig;
	
	
//	@GetMapping("/value")
//	public ResponseEntity<?> message()
//	{
//		System.out.println("inside controller using valye annotation , max:"+max);
//		System.out.println("inside controller using env variable , min:"+env.getProperty("myservice.minimum"));
//     return ResponseEntity.ok(cloudconfig.getValue());
//	}
	
	@GetMapping("/value")
	public ResponseEntity<?> message()
	{
		int min=Integer.parseInt(env.getProperty("myservice.minimum"));
		System.out.println("inside controller using valye annotation , max:"+max);
		System.out.println("inside controller using env variable , min:"+min);
     return ResponseEntity.ok("maximum:"+max+"minimum:"+min);
	}
}
