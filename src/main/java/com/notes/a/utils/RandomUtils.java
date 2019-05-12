package com.notes.a.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomUtils {

	public static String createRandomNote() {
		List<String> inputStrings = new ArrayList<String>(
				Arrays.asList("one", "two", "three", "comments", "random", "Bho", "Sweaty"));
		StringBuilder stringBuilder = new StringBuilder("");
		final int SIZE = inputStrings.size();
		for (int i = 0; i < SIZE; i++) {
			stringBuilder.append((String) getRandomElement(inputStrings));
			stringBuilder.append(' ');
			if (i == SIZE - 1) {
				continue;
			}
		}
		return stringBuilder.toString();
	}

	public static String createRandomUser() {
		List<String> inputStrings = new ArrayList<String>(Arrays.asList("Jhon", "Leslie", "Hanna", "Kan"));
		return getRandomElement(inputStrings).toString() + "_" + String.valueOf(((Math.random() * 500_000_000) + 1));
	}

	public static Integer createRandomVote() {
		List<Integer> inputInteger = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		return (Integer) getRandomElement(inputInteger);
	}

	public static String createRandomTitle() {
		return "t-" + new Random().nextInt(10000);
	}

	public static Object getRandomElement(List<?> list) {
		Random rand = new Random();
		return list.get(rand.nextInt(list.size()));
	}

}
