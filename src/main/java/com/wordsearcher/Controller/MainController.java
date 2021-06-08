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
public class MainController {
	
	@Autowired
	private WordRepository wordRepository;

	/*
	 * The following functions handle getting the input from the user
	 * They are mirrored to allow the results to appear on the same page as the query
	 */

	@GetMapping("/query_form")
	public String queryForm(Model model) {
		WordQuery Word = new WordQuery("", "");
		model.addAttribute("Word", Word);
		return "query_form";
	}

	@GetMapping("/query_results")
	public String queryFormMirror(Model model) {
		WordQuery Word = new WordQuery("", "");
		model.addAttribute("Word", Word);
		return "query_results";
	}
	
	@PostMapping("/query_form")
	public String submitQuery(@ModelAttribute("Word") WordQuery Word, Model model) {
		List<String> wordList = wordRepository.getAllPossible(Word.getWord(), Word.getMode());
		
		model.addAttribute("wordList", wordList);

		return "query_results";
	}

	@PostMapping("/query_results")
	public String submitQeuryMirror(@ModelAttribute("Word") WordQuery Word, Model model) {
		List<String> wordList = wordRepository.getAllPossible(Word.getWord(), Word.getMode());
		
		model.addAttribute("wordList", wordList);
		
		return "query_results";
	}
}