package com.notes.app.service;

import com.notes.app.annotations.TrackTime;
import com.notes.app.domain.NotesStatus;
import com.notes.app.entity.Notes;
import com.notes.app.entity.User;
import com.notes.app.entity.Votes;
import com.notes.app.repository.NotesRepository;
import com.notes.app.repository.VotesRepository;
import com.notes.app.utils.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

@Service
@Slf4j
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NotesRepository notesRepository;
	@Autowired
	private VotesRepository votesRepository;

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * https://www.springboottutorial.com/hibernate-jpa-tutorial-with-spring-boot-
	 * starter-jpa
	 */
	@Override
	@Transactional
	public void addElements() {

		User user1 = new User(RandomUtils.createRandomUser());
		Notes note1 = new Notes(RandomUtils.createRandomTitle(), RandomUtils.createRandomNote(), user1);
		Votes vote1 = new Votes(RandomUtils.createRandomVote(), note1);
		votesRepository.save(vote1);
	}

	@Override
	@TrackTime
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void bulkPopulateTransaction(final int MAX_INSERT) {
		for (int i = 0; i < MAX_INSERT; i++) {
			addElements();
		}
	}


	// https://www.baeldung.com/spring-data-jpa-pagination-sorting
	@Override
	@Transactional(readOnly = true)
	public Page<Notes> findNotesByPageableOrderById(int numNotes) {
		Pageable pageable = PageRequest.of(0, numNotes, Sort.by("id"));
		return this.notesRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void processingNullNotesStatus() {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();

		// create update
		CriteriaUpdate<Notes> update = cb.createCriteriaUpdate(Notes.class);

		// set the root class
		Root<Notes> root = update.from(Notes.class);

		// set update and where clause
		update.set("notesStatus", NotesStatus.APPROVED);
		update.where(cb.isNull(root.get("notesStatus")));

		// perform update

		log.info("Update n°:{} of Notes", this.entityManager.createQuery(update).executeUpdate());
	}

	@Override
	@Transactional(readOnly = true)
	public Boolean findNullNotesStatus() {
//		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
//		CriteriaQuery<Notes> cr = cb.createQuery(Notes.class);
//		Root<Notes> root = cr.from(Notes.class);
//		cr.select(root).where(cb.isNull(root.get("notesStatus")));
		//	final String criteriaQuery = "SELECT CASE WHEN EXISTS (SELECT n FROM com.notes.a.entity.Notes n WHERE n.notesStatus is null) THEN TRUE ELSE FALSE END";

		//	return (Boolean) entityManager.createQuery(criteriaQuery).setMaxResults(1).getSingleResult();
		return Boolean.FALSE;
	}
/*
	public static void main(String[] args) {
		String note = RandomUtils.createRandomNote();
		String user = RandomUtils.createRandomUser();
		int vote = RandomUtils.createRandomVote();
		String title = RandomUtils.createRandomTitle();
		System.out.println("Title " + title + " note: [" + note + "] user: " + user + " vote: " + vote);
	}
*/
}
