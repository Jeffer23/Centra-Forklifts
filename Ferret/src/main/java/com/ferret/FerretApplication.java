package com.ferret;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.ferret.*"})
public class FerretApplication {

	public static void main(String[] args) {
		SpringApplication.run(FerretApplication.class, args);
	}

}
