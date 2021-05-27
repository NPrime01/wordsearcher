package com.wordsearcher.Model;

public class WordData {
    private String word;

    public WordData(String word) { 
        this.word = word.trim(); 
    }

    public void setWord(String word) { this.word = word; }
    public String getWord() { return this.word; }

    @Override
    public String toString() {
        return String.format("WordData[word='%s']", word);
    }
}
