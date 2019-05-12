package com.notes.a.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.notes.a.annotations.TrackTime;
import com.notes.a.entity.Votes;
import com.notes.a.exception.ResourceNotFoundException;
import com.notes.a.repository.VotesRepository;

import lombok.extern.slf4j.Slf4j;

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
