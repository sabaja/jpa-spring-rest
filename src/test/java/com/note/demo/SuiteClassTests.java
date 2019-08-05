package com.note.demo;

import org.junit.Assume;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	ParameterizedTests.class,
//	ListTests.class 
	})
public class SuiteClassTests {
	
	public static void disableTest() {
		Assume.assumeFalse(System.getProperty("os.name").contains("Linux"));
	}

}
