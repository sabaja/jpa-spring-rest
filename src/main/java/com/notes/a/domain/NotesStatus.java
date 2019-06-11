package com.notes.a.domain;

/**
 * 
 * @author sabaja
 * https://vladmihalcea.com/the-best-way-to-map-an-enum-type-with-jpa-and-hibernate/
 */
public enum NotesStatus {
	PENDING(0), APPROVED(1), SPAM(2);
	
	private final int value;

	public int getValue() {
		return value;
	}

	private NotesStatus(int value) {
		this.value = value;
	}
}
