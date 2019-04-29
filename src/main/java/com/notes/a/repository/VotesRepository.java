package com.notes.a.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notes.a.entity.Votes;

@Repository
public interface VotesRepository extends JpaRepository<Votes, Long> {

}
