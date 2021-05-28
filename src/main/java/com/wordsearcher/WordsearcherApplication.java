package com.wordsearcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.wordsearcher.Repository.WordRepository;

@SpringBootApplication
public class WordsearcherApplication implements CommandLineRunner {

  private static final Logger log = LoggerFactory.getLogger(WordsearcherApplication.class);

  @Autowired
  private WordRepository wordRepository;

  public static void main(String args[]) {
    SpringApplication.run(WordsearcherApplication.class, args);
  }

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public void run(String... strings) throws Exception {

    // Create table
    log.info("Creating table");
    jdbcTemplate.execute("DROP TABLE words IF EXISTS");
    jdbcTemplate.execute("CREATE TABLE words(word VARCHAR(46))");
    
    // Read the file on disk into memory
    List<Object[]> contents = Arrays.asList();

    // NOTE: this way works for loading words.txt after the project is packaged too
    try (InputStream inputStream = getClass().getResourceAsStream("/words.txt");
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        contents = reader.lines()
          .map(word -> word.trim().split(" "))
          .collect(Collectors.toList());
    }
    catch(Exception e) {}

    // Load data from Memory to table in sql
    jdbcTemplate.batchUpdate("INSERT INTO words(word) VALUES (?)", contents);
    log.info("Finished Creating Table");
  }
}
