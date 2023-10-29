
package com.org.telugucineandtvoutdoorunittechniciansunion.init;

import java.io.File;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationUtilities {
	static {
		PropertyConfigurator cnf = new PropertyConfigurator();

		File log4jfile = new File(
				System.getProperty("user.dir") + "\\src\\main\\resources\\AppLoggerConfigPropertes.lcf");
		PropertyConfigurator.configure(log4jfile.getAbsolutePath());
	}

	public static void error(Class<?> className, Exception e, String methodName) {
		Logger logger = LoggerFactory.getLogger(className.getClass());
		logger.error(
				String.valueOf(String.valueOf(className.getName())) + " >> " + methodName + " >> " + e.getMessage());
		e.printStackTrace();

	}

	public static void error(Class<?> className, String methodName, Exception e) {
		Logger logger = LoggerFactory.getLogger(className.getClass());
		logger.error(
				String.valueOf(String.valueOf(className.getName())) + " >> " + methodName + " >> " + e.getMessage());
		e.printStackTrace();

	}

	public static void debug(Class<?> className, String debugMessabe) {
		Logger logger = LoggerFactory.getLogger(className.getClass());
		logger.debug(debugMessabe);
		System.out.println("DEBUG : " + debugMessabe);
	}
}
