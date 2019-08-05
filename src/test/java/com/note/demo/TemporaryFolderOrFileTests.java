package com.note.demo;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class TemporaryFolderOrFileTests {

	  @Rule
	  public TemporaryFolder folder = new TemporaryFolder();

	  @Test
	  public void testUsingTempFolder() throws IOException {
	    folder.newFolder("newfolder");
	    File createdFile = folder.newFile("myfilefile.txt");
	    assertTrue(createdFile.exists());
	  }
	}