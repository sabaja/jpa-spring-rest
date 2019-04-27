package com.notes;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * https://www.callicoder.com/spring-boot-rest-api-tutorial-with-mysql-jpa-hibernate/
 * 
 * @author SabatiniJa
 *
 */
@SpringBootApplication
@EnableJpaAuditing
@ComponentScan("com")
@EntityScan("com")
@EnableJpaRepositories("com")
public class JpaSpringRestApplication {
	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public static void main(String[] args) throws IOException {
		// From down to top
		ConfigurableApplicationContext context = SpringApplication.run(JpaSpringRestApplication.class, args);
		System.out.println("Hit enter to terminate");
		System.in.read();
		context.close();
	}
}
