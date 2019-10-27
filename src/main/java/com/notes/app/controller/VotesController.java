package com.notes.app.controller;

import com.notes.app.annotations.TrackTime;
import com.notes.app.entity.Votes;
import com.notes.app.exception.ResourceNotFoundException;
import com.notes.app.repository.VotesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class VotesController {

	@Autowired
	private VotesRepository votesRepository;

	@GetMapping(value = "/votes")
	@TrackTime
	public List<Votes> getAllVotes() {
		return this.votesRepository.findAll();
	}
	
	@GetMapping(value = "/votes/avg/{notes_id}")
	public int getAvgVotesByNotesId(@PathVariable(value = "notes_id") Long notes_id) {
		return votesRepository.getAverageOfVotesByNotesId(notes_id);
	}

	@GetMapping(value = "/votes/count")
	public int getCountVotes() {
		return votesRepository.countVotesRecord();
	}

	
	@GetMapping(value = "/votes/{id}")
	public Votes getVotesById(@PathVariable(value = "id") Long id) {
		return votesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vote", "id", id));
	}
	
	@PostMapping(value = "/votes")
	public Votes createVote(@Valid @RequestBody Votes votes) {
		log.info("saving.. {}", votes);
		return votesRepository.save(votes);
	}
	@PutMapping(value = "/votes/{id}")
	public Votes updateVoteById(@PathVariable(value = "id") Long id, @Valid @RequestBody Votes votes) {
		Votes vote = votesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vote", "id", id));
		vote.setVote(votes.getVote());
		vote.setNotes(votes.getNotes());
		return votesRepository.save(vote);
	}
	
	@DeleteMapping(value = "/votes/{id}")
	public ResponseEntity<?> deleteVoteById(@PathVariable(value = "id") Long id) {
		Votes vote = votesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vote", "id", id));
		votesRepository.delete(vote);
		return ResponseEntity.ok().build();
	}

}
