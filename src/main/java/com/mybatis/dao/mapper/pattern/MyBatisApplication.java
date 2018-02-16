package com.mybatis.dao.mapper.pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * 
 * @author Michael Somers, mike1somers@gmail.com
 *
 */

@SpringBootApplication
public class MyBatisApplication {

	private static final Logger logger = LoggerFactory
			.getLogger(MyBatisApplication.class);

	public static void main(String[] args) {

		logger.debug("Starting...");
		SpringApplication.run(MyBatisApplication.class, args);
		logger.debug("Success!");
	}
}
