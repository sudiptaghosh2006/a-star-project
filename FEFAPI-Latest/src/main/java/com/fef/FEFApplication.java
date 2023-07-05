package com.fef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FEFApplication {

	private static Logger logger = LoggerFactory.getLogger(FEFApplication.class);

	public static void main(String[] args) {
		logger.debug("starting client");
		SpringApplication.run(FEFApplication.class);
	}

}
