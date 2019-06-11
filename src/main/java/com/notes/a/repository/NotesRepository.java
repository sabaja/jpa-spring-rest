package com.notes.a.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notes.a.entity.Notes;

/**
 * https://www.callicoder.com/spring-boot-rest-api-tutorial-with-mysql-jpa-hibernate/
 * 
 * You don’t need to implement JpaRepository's methods. They are already implemented by
 * Spring Data JPA’s SimpleJpaRepository. This implementation is plugged in by
 * Spring automatically at runtime.
 * 
 * @author SabatiniJa
 *
 */
@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {
	
//	@Query("select n.id from com.notes.a.entity.Notes n where n.notesStatus is null" )
//	public List<Long> findNullNotesStatus();
}
