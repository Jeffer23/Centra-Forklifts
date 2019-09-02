package com.cf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.cf.*"})
public class CentraForkliftApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentraForkliftApplication.class, args);
	}

}
