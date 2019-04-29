package com.notes.a.entity;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "VOTES")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = "createdAt", allowGetters = true)
@Getter
@Setter
@EqualsAndHashCode
public class Votes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -642008647842664506L;

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "VOTE", nullable = false)
	@Min(0)
	@Max(10)
	private Integer vote;

	// Automatic conversion type
	@Column(name = "CREATE_DATE", nullable = false)
	@CreatedDate
	private Instant createdAt;

}
