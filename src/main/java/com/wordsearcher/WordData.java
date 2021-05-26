package com.wordsearcher;

public class WordData {
    private long id;
    private String word;

    public WordData(String word) { 
        this.word = word; 
    }

    public void setId(Long id) { this.id = id; }
    public long setId() { return this.id; }
    public void setWord(String word) { this.word = word; }
    public String getWord() { return this.word; }

    @Override
    public String toString() {
        return String.format("WordData[id=%d, word='%s']", id, word);
    }
}
