package com.note.demo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.aspectj.lang.annotation.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ListTests {

	
	@BeforeClass
	public static void beforeClass() {
		log.info("beforeClass");
	}

	@Before
	public void before() {
		log.info("before");
	}

	@AfterClass
	public static void afterClass() {
		log.info("afterClass");
	}

	@After(value = "after")
	public static void after() {
		log.info("after");
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void test1() {
		System.out.println("Test 1");
		assertTrue(true);
		assertFalse(false);
	}

	@Test
	public void test2() {
		log.info("Test 2");
		assertTrue(false);
	}

	@Test
	public void test3() throws Exception {
		log.info("Test 3");
		throw new Exception();
	}

	@Test(timeout = 100)
	public void xyzTesting() throws InterruptedException {
		log.info("Test xyzTesting");
		// fallirà per timeout scaduto
		Thread.sleep(200);
		assertTrue(true);
	}

	@Test(expected = java.lang.Exception.class)
	public void nuovoTest() throws Exception {
		log.info("Test nuovoTest");
		assertTrue(true);
		throw new Exception();
	}

}
