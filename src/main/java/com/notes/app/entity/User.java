package com.notes.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "USER", indexes = @Index(columnList = "id", name = "user_id_hidx"))
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt", "notes" }, allowGetters = true)
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class User implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 8593336999639803120L;

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Size(min = 1, max = 256)
	@Column(name = "USER_NAME", nullable = false, unique = true)
	private String userName;

	@OneToMany(mappedBy = "user")
	private List<Notes> notes;

	public User(@NotNull @Size(min = 1, max = 16) String userName) {
		super();
		this.userName = userName;
		this.notes = null;
	}

	public User(@NotNull @Size(min = 1, max = 16) String userName, List<Notes> notes) {
		super();
		this.userName = userName;
		this.notes = notes;
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
