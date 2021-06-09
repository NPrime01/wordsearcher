package com.wordsearcher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.wordsearcher.Model.WordData;
import com.wordsearcher.Repository.WordRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

@SpringBootTest
class WordsearcherApplicationTests {
	@Autowired
	WordRepository wordRepository;

	@Test
	void DatabaseLoadsCorrectly() {

		List<Object[]> tokens = null;
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("words.txt").getFile());
			tokens = Files.lines(file.toPath())
				.map(word -> word.split(" "))
				.collect(Collectors.toList()); 
		}
		catch(IOException e) {
			// fail test if database not found
			fail("Database not found");
		}
		
		// make sure the entire database loaded into memory
		assertEquals(tokens.size(), wordRepository.getAllPossible("", "StartsWith").size());
	}

	@Test
	void startsWithTest() {
		// collect test data
		List<String> actual = wordRepository.getAllPossible("Ry", "Starts With");

		// set up example data
		List<String> expected = Arrays.asList("Ryan", "Ryann", "Ryazan", "Rybinsk", "Rycca",
			"Rychard", "Rydal", "Rydberg", "Rydder", "Ryde", "Ryder", "Ryderwood", "Rye",
			"Ryeland", "Ryland", "Ryle", "Ryley", "Rymandra", "Rynchospora", "Ryon", "Rysler", 
			"Ryswick", "Ryter", "Rytina", "Ryukyu", "Ryun", "Ryunosuke", "Ryurik");

		// make sure the querys match
		assertEquals(expected, actual);
	}

	@Test
	void endsWtihTest() {
		// collect test data
		List<String> actual = wordRepository.getAllPossible("axe", "Ends With");
		
		// set up expected data
		List<String> expected = Arrays.asList("Badaxe", "battle-axe", "breakaxe", "broadaxe",
			"broad-axe", "curtaxe", "equiaxe", "pickaxe", "poleaxe", "pole-axe", "Saxe", "scramasaxe",
			"stone-axe", "tommy-axe");
		
		// check actual vs expected
		assertEquals(expected, actual);
	}

	@Test 
	void containsTest() {
		// collect test data
		List<String> actual = wordRepository.getAllPossible("zoom", "Contains");

		// get actual data
		List<String> expected = Arrays.asList("bazooms", "zoom", "zoomagnetic", "zoomagnetism",
			"zoomancy", "zoomania", "zoomanias", "zoomantic", "zoomantist", "zoomechanical",
			"zoomechanics", "zoomed", "zoomelanin", "zoometry", "zoometric", "zoometrical", 
			"zoometries", "zoomimetic", "zoomimic", "zooming", "zoomorph", "zoomorphy", "zoomorphic",
			"zoomorphism", "zoomorphize", "zoomorphs", "zooms");
		
		// check actual against expected
		assertEquals(expected, actual);
	}
}
