package com.example.GateWay.API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GateWayApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateWayApiApplication.class, args);
	}

}
