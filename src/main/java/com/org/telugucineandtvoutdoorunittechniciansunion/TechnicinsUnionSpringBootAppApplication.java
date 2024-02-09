package com.org.telugucineandtvoutdoorunittechniciansunion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")

@ComponentScan(basePackages= {"com.org.telugucineandtvoutdoorunittechniciansunion",
		"com.org.telugucineandtvoutdoorunittechniciansunion.controller",
		"com.org.telugucineandtvoutdoorunittechniciansunion.service"		
})
@ServletComponentScan
public class TechnicinsUnionSpringBootAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnicinsUnionSpringBootAppApplication.class, args);

	}
}
