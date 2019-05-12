package com.notes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.notes.a.entity.Notes;
import com.notes.a.entity.User;
import com.notes.a.entity.Votes;
import com.notes.a.repository.NotesRepository;
import com.notes.a.repository.UserRepository;
import com.notes.a.repository.VotesRepository;
import com.notes.a.service.NoteService;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class JpaSpringRestApplication implements CommandLineRunner{

	@SuppressWarnings("unused")
	@Autowired
	private NoteService noteService;

	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext context = SpringApplication.run(JpaSpringRestApplication.class, args);
		log.info("NO populating db");
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
	}
	
}
