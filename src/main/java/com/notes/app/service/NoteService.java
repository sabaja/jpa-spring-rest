package com.notes.app.service;

import com.notes.app.entity.Notes;
import org.springframework.data.domain.Page;

public interface NoteService {

	public abstract void addElements();
	public abstract void bulkPopulateTransaction(final int numElement);
	public abstract Page<Notes> findNotesByPageableOrderById(final int numNotes);
	public abstract void processingNullNotesStatus();

	public abstract Boolean findNullNotesStatus();

}
