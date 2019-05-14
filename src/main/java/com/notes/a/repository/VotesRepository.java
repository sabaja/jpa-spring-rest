package com.notes.a.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.notes.a.entity.Votes;

@Repository
public interface VotesRepository extends JpaRepository<Votes, Long> {
	
	@Query("SELECT AVG(v.vote) FROM Votes v where v.notes.id=:notes_id")
	int getAverageOfVotesByNotesId(final Long notes_id);

}
