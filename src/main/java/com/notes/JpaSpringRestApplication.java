package com.notes;

import com.notes.app.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;

/**
 * https://www.callicoder.com/spring-boot-rest-api-tutorial-with-mysql-jpa-hibernate/
 *
 * @author SabatiniJa
 */
@SpringBootApplication
@EnableJpaAuditing
@ComponentScan("com")
@EntityScan("com")
@EnableJpaRepositories("com")
//https://www.baeldung.com/spring-async
@EnableAsync
@Slf4j
public class JpaSpringRestApplication implements CommandLineRunner {

	@SuppressWarnings("unused")
	@Autowired
	private NoteService noteService;

	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext context = SpringApplication.run(JpaSpringRestApplication.class, args);

		System.out.println("Hit enter to terminate");
		System.in.read();
		context.close();
	}

	@Override
	public void run(String... arg0) throws Exception {
		// From down to top
//		noteService.addElements();
//		noteService.bulkPopulateTransaction(30_000);
//		log.info("Feedding DB... done");
		log.info("NO populating db");
	}
}
