package com.notes.a.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.notes.a.service.NoteService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	private NoteService noteService;
	/**
	 * This event is executed as late as conceivably possible to indicate that the
	 * application is ready to service requests.
	 */
	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {
//		boolean flag = this.notesRepository.findNullNotesStatus();
		boolean flag = this.noteService.findNullNotesStatus();
		
		if (flag ) {
			log.info("Starting processing null notesStatus..");
			this.noteService.processingNullNotesStatus();
		}else {
			log.info("Nothing to do");
		}
		return;
	}

	public NoteService getNoteService() {
		return noteService;
	}

	@Autowired
	public void setNoteService(NoteService noteService) {
		this.noteService = noteService;
	}
}