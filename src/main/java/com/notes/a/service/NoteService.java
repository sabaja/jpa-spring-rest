package com.notes.a.service;

import org.springframework.data.domain.Page;

import com.notes.a.entity.Notes;

public interface NoteService {
	
	public abstract void addElements();
	public abstract void bulkPopulateTransaction(final int numElement);
	public abstract Page<Notes> findNotesByPageableOrderById(final int numNotes);
	public abstract void processingNullNotesStatus();
	public abstract Boolean findNullNotesStatus();	

}
