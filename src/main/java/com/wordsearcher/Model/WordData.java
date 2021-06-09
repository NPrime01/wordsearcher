package com.wordsearcher.Model;

/*
This class Stores the data for an individual word. Allows interfacing with the database
 */
public class WordData {
    private String word;

    public WordData(String word) { 
        this.word = word.trim().replace("\n", ""); 
    }

    public void setWord(String word) { this.word = word; }
    public String getWord() { return this.word; }

    @Override
    public String toString() {
        return String.format("WordData[word='%s']", word);
    }
}
