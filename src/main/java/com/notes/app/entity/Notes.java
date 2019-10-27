package com.notes.app.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notes.app.domain.NotesStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * https://www.callicoder.com/spring-boot-rest-api-tutorial-with-mysql-jpa-hibernate/
 * 
 * @author SabatiniJa
 *
 */
@Entity
@Table(name = "NOTES", indexes = @Index(columnList = "id", name = "notes_id_hidx"))
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Notes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2828908919459234160L;

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "TITLE")
	@NotBlank
	private String title;

	@Column(name = "CONTENT")
	@NotBlank
	private String content;

	// Automatic conversion type
	@Column(name = "CREATE_DATE", nullable = false)
	@CreatedDate
	private Instant createdAt;

	// Automatic conversion type
	@Column(name = "UPDATE_DATE", nullable = false)
	@LastModifiedDate
	private Instant updatedAt;

	// Bidirectional relationships for performance
	// https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
	@OneToMany(mappedBy = "notes", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Votes> votes;

	// https://stackoverflow.com/questions/20813496/tomcat-exception-cannot-call-senderror-after-the-response-has-been-committed
	// senza JsonIgnore hibernate infinite loop for loading parent in child
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private User user;

	// https://vladmihalcea.com/the-best-way-to-map-an-enum-type-with-jpa-and-hibernate/
	@Enumerated
	@Column(name = "NOTES_STATUS", columnDefinition = "smallint", nullable = true)
	private NotesStatus notesStatus;

	public Notes(@NotBlank String title, @NotBlank String content, User user) {
		super();
		this.title = title;
		this.content = content;
		this.user = user;
		this.votes = null;
	}

	public Notes(@NotBlank String title, @NotBlank String content, User user, List<Votes> votes, NotesStatus notesStatus) {
		this(title, content, user);
		this.setVotes(votes);
		this.setNotesStatus(notesStatus);
	}

	@Override
	public String toString() {
		try {
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
