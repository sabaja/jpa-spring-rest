package com.note.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.aspectj.lang.annotation.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;
import org.springframework.test.context.ContextConfiguration;

import lombok.extern.slf4j.Slf4j;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@ContextConfiguration
@RunWith(Parameterized.class)
public class ParameterizedTests {

	private int p1;
	private int p2;
	
	public ParameterizedTests(int p1, int p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	// creates the test data
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { 1, 2 }, { 5, 3 }, { 121, 4 } };
		return Arrays.asList(data);
	}

	@Test
	public void testMultiplyException() {
		MyClass tester = new MyClass();
//		log.info("Test multi");
		assertEquals("Result",p1 * p2, tester.multiply(p1, p2));
	}

}
