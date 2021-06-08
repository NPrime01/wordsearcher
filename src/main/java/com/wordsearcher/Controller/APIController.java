package com.wordsearcher.Controller;

import com.wordsearcher.Model.WordAPI;
import com.wordsearcher.Repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

    @Autowired
    private WordRepository wordRepository;

    @GetMapping("/api/sw")
    public WordAPI swAPI(@RequestParam(value="word", defaultValue="hire") String word) {
        return new WordAPI(word, "Starts With", wordRepository.getAllPossible(word, "Starts With"));
    }

    @GetMapping("/api/ew")
    public WordAPI ewAPI(@RequestParam(value="word", defaultValue="hire") String word) {
        return new WordAPI(word, "Ends With", wordRepository.getAllPossible(word, "Ends With"));
    }

    @GetMapping("/api/co")
    public WordAPI coAPI(@RequestParam(value="word", defaultValue="hire") String word) {
        return new WordAPI(word, "Contains", wordRepository.getAllPossible(word, "Contains"));
    }
}
