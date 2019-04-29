package com.notes.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notes.entity.Notes;
import com.notes.exception.ResourceNotFoundException;
import com.notes.repository.NotesRepository;

/**
 * https://www.callicoder.com/spring-boot-rest-api-tutorial-with-mysql-jpa-hibernate/
 * 
 * @RestController is a combination of Spring’s @Controller and @ResponseBody
 *                 annotations. It exposes the annotated bean’s methods as HTTP
 *                 endpoints using metadata furnished by the @RequestMapping
 *                 annotation on each method. A method will be put into service
 *                 if an incoming HTTP request matches the qualifications
 *                 stipulated by the @RequestMapping annotation on the method.
 * @author SabatiniJa
 *
 */

@RestController
@RequestMapping("/jpa-spring-rest")
public class NotesController {

	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Autowired
	private NotesRepository notesRepository;

	@GetMapping(value = "/notes")
	public List<Notes> getAllNotes() {
		return this.notesRepository.findAll();
	}

	// The @RequestBody annotation is used to bind the request body with a method
	// parameter.
	@PostMapping(value = "/notes")
	public Notes createNotes(@Valid @RequestBody Notes notes) {
		return this.notesRepository.save(notes);
	}

	@GetMapping(value = "/notes/{id}")
	public Notes getNotesById(@PathVariable(value = "id") Long id) {
		return this.notesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
	}

	@PutMapping(value = "/notes/{id}")
	public Notes updateNotesById(@PathVariable(value = "id") Long id, @Valid @RequestBody Notes notes) {
		Notes note = notesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
		note.setTitle(notes.getTitle());
		note.setContent(notes.getContent());
		return notesRepository.save(note);
	}
	
	
	@DeleteMapping(value = "/notes/{id}")
	public ResponseEntity<?> deleteNoteById(@PathVariable(value = "id") Long id) {
		Notes note = notesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
		notesRepository.delete(note);
		return ResponseEntity.ok().build();
	}
}
