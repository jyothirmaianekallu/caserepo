package com.innominds.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan( basePackages={"com.innominds"})
@EnableAutoConfiguration
	public class Application  {

		public static void main(String[] args) throws Exception {
			SpringApplication.run(Application.class, args);
		}

}

