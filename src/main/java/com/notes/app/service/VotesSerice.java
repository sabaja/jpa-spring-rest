package com.notes.app.service;

import com.notes.app.entity.Votes;

import java.util.List;

public interface VotesSerice {

	int getAverageOfVotesByNotesId(final Long notesId);

	int countVotesRecord();

	List<Votes> findAll();

	Votes findById(Long id);

	Votes save(Votes votes);

	void delete(Votes vote);

	Votes updateVoteById(Long id, Votes votes);

	void deleteVoteById(Long id);
}
