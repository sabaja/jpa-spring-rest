package com.notes.app.model.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class VotesDto {
	private final Long id;
	private final Integer vote;
	private final Instant createdAt;
}
