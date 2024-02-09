package com.kp.springconfigclient.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
@ConfigurationProperties("myservice")
public class CloudConfig {

	private int minimum;
	private int maximum;
   
	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	
    public String getValue() {
    	System.out.println("maximum:"+maximum);
    	System.out.println("minimum:"+minimum);
    	
    	//System.out.println(message1);
    	return "maximum:"+maximum+",minimum:"+minimum;
    }
}
