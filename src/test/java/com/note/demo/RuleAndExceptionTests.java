package com.note.demo;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RuleAndExceptionTests {
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void throwsIllegalArgumentExceptionIfNegativeFactors() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Negative value not allowed");
		MyClass myClass = new MyClass();
		myClass.multiply(1, 1);
	}
}
