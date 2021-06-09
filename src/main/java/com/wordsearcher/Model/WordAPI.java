package com.wordsearcher.Model;

import com.wordsearcher.Repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/*
This class stores the data requested by a GET request to the API.
The main difference between this class and WordQuery is that we perform the database Query on initialization.
 */
public class WordAPI {

    private String word;
    private String mode;
    private List<String> results;

    public WordAPI(String word, String mode, List<String> results) {
        this.word = word;
        this.mode = mode;
        this.results = results;
    }

    public String getWord() { return word; }
    public String getMode() { return mode; }
    public List<String> getResults() { return results; }
}
