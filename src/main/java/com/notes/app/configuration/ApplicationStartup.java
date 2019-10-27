package com.notes.app.configuration;

import com.notes.app.repository.NotesRepository;
import com.notes.app.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private NotesRepository notesRepository;

	@Autowired
	private NoteService noteService;


	/**
	 * This event is executed as late as conceivably possible to indicate that the
	 * application is ready to service requests.
	 */
	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {

		boolean flag = this.isNotesExist();
		
		if (flag ) {
			log.info("Starting processing null notesStatus..");
			this.noteService.processingNullNotesStatus();
		}else {
			log.info("Nothing to do");
		}
		return;
	}

	private Boolean isNotesExist() {
		return this.notesRepository.findAll()
				.stream()
				.anyMatch(Objects::nonNull);
	}

}