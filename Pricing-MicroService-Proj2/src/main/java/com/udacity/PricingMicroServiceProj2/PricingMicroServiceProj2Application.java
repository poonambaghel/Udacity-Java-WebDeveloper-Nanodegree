package com.udacity.PricingMicroServiceProj2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PricingMicroServiceProj2Application {

	public static void main(String[] args) {
		SpringApplication.run(PricingMicroServiceProj2Application.class, args);
	}

}
