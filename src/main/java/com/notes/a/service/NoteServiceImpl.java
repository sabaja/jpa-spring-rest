package com.notes.a.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.notes.a.annotations.TrackTime;
import com.notes.a.entity.Notes;
import com.notes.a.entity.User;
import com.notes.a.entity.Votes;
import com.notes.a.repository.NotesRepository;
import com.notes.a.repository.VotesRepository;
import com.notes.a.utils.RandomUtils;

@Service
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
		for(int i = 0 ; i < MAX_INSERT ; i++) {
			addElements();
		}
	}

	@Override
	//https://www.baeldung.com/spring-data-jpa-pagination-sorting
	public Page<Notes> findNotesByPageableOrderById(int numNotes) {
		Pageable pageable = PageRequest.of(0, numNotes, Sort.by("id"));
		Page<Notes>  pageOfNotes = this.notesRepository.findAll(pageable);
		return pageOfNotes;
	}
	/*
	   	CriteriaBuilder cri = this.entityManager.unwrap(Session.class).getCriteriaBuilder();
		CriteriaQuery<Notes> query = cri.createQuery(Notes.class);
//		cri.add(Restrictions.like(propertyName, value, matchMode))
	 * @param args
	 */
	

	public static void main(String[] args) {
		String note = RandomUtils.createRandomNote();
		String user = RandomUtils.createRandomUser();
		int vote = RandomUtils.createRandomVote();
		String title = RandomUtils.createRandomTitle();
		System.out.println("Title " + title +  " note: [" + note + "] user: " + user + " vote: " + vote);
	}
}
