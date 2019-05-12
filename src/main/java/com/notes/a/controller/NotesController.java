package com.notes.a.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.notes.a.annotations.TrackTime;
import com.notes.a.entity.Notes;
import com.notes.a.exception.ResourceNotFoundException;
import com.notes.a.repository.NotesRepository;
import com.notes.a.service.NoteService;

import lombok.extern.slf4j.Slf4j;

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
@RequestMapping("/api")
@Slf4j
public class NotesController {

	
	@Autowired
	private NotesRepository notesRepository;
	
	@Autowired
	private NoteService noteService;

	@GetMapping(value = "/notes")
	@TrackTime
	public List<Notes> getAllNotes() {
		return this.notesRepository.findAll();
	}

	// The @RequestBody annotation is used to bind the request body with a method
	// parameter.
	@PostMapping(value = "/notes")
	public Notes createNotes(@Valid @RequestBody Notes notes) {
		log.info("saving.. {}", notes);
		return this.notesRepository.save(notes);
	}

	@GetMapping(value = "/notes/{id}")
	public Notes getNotesById(@PathVariable(value = "id") Long id) {
		return this.notesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
	}

	@GetMapping(value = "/notes/tot")
	public List<Notes> getSomeNote(@RequestParam("num") int numNotes){
		Page<Notes> pages = this.noteService.findNotesByPageableOrderById(numNotes);
		return pages.stream().collect(Collectors.toList());
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
