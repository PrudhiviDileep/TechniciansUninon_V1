package com.org.telugucineandtvoutdoorunittechniciansunion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.org.telugucineandtvoutdoorunittechniciansunion")
@ServletComponentScan
public class TechnicinsUnionSpringBootAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnicinsUnionSpringBootAppApplication.class, args);

	}
}
