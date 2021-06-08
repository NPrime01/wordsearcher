package com.wordsearcher.Model;

import com.wordsearcher.Repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
