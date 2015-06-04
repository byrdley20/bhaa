package com.coptertours;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan
@EnableJpaRepositories
@EnableAutoConfiguration
@SpringBootApplication
public class BhaaApplication {

	public static void main(String[] args) {
		System.setProperty("com.sun.media.jai.disableMediaLib", "true");
		SpringApplication.run(BhaaApplication.class, args);
	}
}