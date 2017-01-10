package edu.oakland;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apereo.portal.soffit.renderer.SoffitApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.cache.annotation.EnableCaching; 


@Configuration
@EnableCaching
@ComponentScan("edu.oakland")
@SoffitApplication
@SpringBootApplication
public class OuRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(OuRedisApplication.class, args);
	}
}
