package com.wordsearcher.Model;

/*
This class stores data from a user using the UI making a query.
Unlike the API use case, we don't want to make the actual database query here.
 */
public class WordQuery {
    private String mode;
    private String word;

    public WordQuery(String mode, String word) {
        this.mode = mode;
        this.word = word;
    }

    public String getMode() { return mode; }
    public String getWord() { return word; }
}
