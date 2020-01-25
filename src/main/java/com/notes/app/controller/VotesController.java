package com.notes.app.controller;

import com.notes.app.annotations.TrackTime;
import com.notes.app.entity.Votes;
import com.notes.app.service.VotesSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("/api")
public class VotesController {

	@Autowired
	private VotesSerice votesService;

	@GetMapping(value = "/votes")
	@TrackTime
	public List<Votes> getAllVotes() {
		return this.votesService.findAll();
	}

	@GetMapping(value = "/votes/avg/{notes_id}")
	public int getAvgVotesByNotesId(@PathVariable(value = "notesId") Long notesId) {
		return votesService.getAverageOfVotesByNotesId(notesId);
	}

	@GetMapping(value = "/votes/count")
	public int getCountVotes() {
		return votesService.countVotesRecord();
	}


	@GetMapping(value = "/votes/{id}")
	public Votes getVotesById(@PathVariable(value = "id") Long id) {
		return votesService.findById(id);
	}

	@PostMapping(value = "/votes")
	public Votes createVote(@Valid @RequestBody Votes votes) {
		return votesService.save(votes);
	}

	@PutMapping(value = "/votes/{id}")
	public Votes updateVoteById(@PathVariable(value = "id") Long id, @Valid @RequestBody Votes votes) {
		return votesService.updateVoteById(id, votes);
	}

	@DeleteMapping(value = "/votes/{id}")
	public ResponseEntity deleteVoteById(@PathVariable(value = "id") Long id) {
		votesService.deleteVoteById(id);
		return ok().build();
	}

}
