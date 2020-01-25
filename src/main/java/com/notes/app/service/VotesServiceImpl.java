package com.notes.app.service;

import com.notes.app.entity.Votes;
import com.notes.app.exception.ResourceNotFoundException;
import com.notes.app.repository.VotesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class VotesServiceImpl implements VotesSerice {

	public static final String ID = "id";
	public static final String VOTE = "Vote";

	@Autowired
	private VotesRepository votesRepository;

	@Override
	public int getAverageOfVotesByNotesId(Long notesId) {
		return votesRepository.getAverageOfVotesByNotesId(notesId);
	}

	@Override
	public int countVotesRecord() {
		return votesRepository.countVotesRecord();
	}

	@Override
	public List<Votes> findAll() {
		return votesRepository.findAll();
	}

	@Override
	public Votes findById(Long id) {
		return votesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(VOTE, ID, id));
	}

	@Override
	public Votes save(Votes votes) {
		log.info("saving.. {}", votes);
		return votesRepository.save(votes);
	}

	@Override
	public void delete(Votes vote) {
		votesRepository.delete(vote);
	}

	@Override
	public Votes updateVoteById(Long id, Votes votes) {
		Votes vote = votesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(VOTE, ID, id));
		vote.setVote(votes.getVote());
		vote.setNotes(votes.getNotes());
		return votesRepository.save(vote);
	}

	@Override
	public void deleteVoteById(Long id) {
		Votes vote = votesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(VOTE, ID, id));
		votesRepository.delete(vote);
	}
}
