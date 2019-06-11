package com.notes.a.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.notes.a.annotations.TrackTime;
import com.notes.a.domain.NotesStatus;
import com.notes.a.entity.Notes;
import com.notes.a.entity.User;
import com.notes.a.entity.Votes;
import com.notes.a.repository.NotesRepository;
import com.notes.a.repository.VotesRepository;
import com.notes.a.utils.RandomUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NotesRepository notesRepository;
//	@Autowired
//	private UserRepository userRepository;
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

	@Override
	// https://www.baeldung.com/spring-data-jpa-pagination-sorting
	@Transactional(readOnly = true)
	public Page<Notes> findNotesByPageableOrderById(int numNotes) {
		Pageable pageable = PageRequest.of(0, numNotes, Sort.by("id"));
		Page<Notes> pageOfNotes = this.notesRepository.findAll(pageable);
		return pageOfNotes;
	}
	/*
	 * CriteriaBuilder cri =
	 * this.entityManager.unwrap(Session.class).getCriteriaBuilder();
	 * CriteriaQuery<Notes> query = cri.createQuery(Notes.class); //
	 * cri.add(Restrictions.like(propertyName, value, matchMode))
	 * 
	 * @param args
	 */

	@Override
	@Transactional
	public void processingNullNotesStatus() {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();

        // create update
        CriteriaUpdate<Notes> update = cb.
        createCriteriaUpdate(Notes.class);

        // set the root class
        Root<Notes> e = update.from(Notes.class);

        // set update and where clause
        update.set("notesStatus", NotesStatus.APPROVED);
        update.where(cb.isNull(e.get("notesStatus")));

        // perform update
        
        log.info("Update n°:{} of Notes", this.entityManager.createQuery(update).executeUpdate());
		return;
	}

	public static void main(String[] args) {
		String note = RandomUtils.createRandomNote();
		String user = RandomUtils.createRandomUser();
		int vote = RandomUtils.createRandomVote();
		String title = RandomUtils.createRandomTitle();
		System.out.println("Title " + title + " note: [" + note + "] user: " + user + " vote: " + vote);
	}

}
