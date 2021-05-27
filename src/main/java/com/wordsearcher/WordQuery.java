package com.wordsearcher;

public class WordQuery {
    private String mode;
    private String word;

    public WordQuery(String mode, String word) {
        this.mode = mode;
        this.word = word;
    }

    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }
    public String getWord() { return word; }
    public void setWord(String word) {this.word = word; }
}
