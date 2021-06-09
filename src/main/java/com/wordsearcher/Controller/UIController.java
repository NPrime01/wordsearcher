package com.wordsearcher.Controller;

import java.util.List;
import java.util.stream.Collectors;

import com.wordsearcher.Model.WordQuery;
import com.wordsearcher.Repository.WordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UIController {
	
	@Autowired
	private WordRepository wordRepository;

	/*
	Called when the user navigates to the UI.
	Sets up an empty query for the user to enter their details
	 */
	@GetMapping("/query")
	public String queryForm(Model model) {
		WordQuery Word = new WordQuery("", "");
		model.addAttribute("Word", Word);
		return "query";
	}

	/*
	Called when the user hits the 'Submit Query' button.
	Adds the word list to the model and returns the page.
	 */
	@PostMapping("/query")
	public String submitQuery(@ModelAttribute("Word") WordQuery Word, Model model) {
		List<String> wordList = wordRepository.getAllPossible(Word.getWord(), Word.getMode());
		
		model.addAttribute("wordList", wordList);

		return "query";
	}
}