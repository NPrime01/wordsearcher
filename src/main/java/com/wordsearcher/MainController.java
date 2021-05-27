package com.wordsearcher;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {
	
	@Autowired
	private WordRepository wordRepository;
	
	@GetMapping("/query_form")
	public String QueryForm(Model model) {
		WordData Word = new WordData("");
		model.addAttribute("Word", Word);
		return "/query_form";
	}
	
	@PostMapping("/query_form")
	public String SubmitQuery(@ModelAttribute("Word") WordData Word, Model model) {
		List<String> wordList = wordRepository.getAllPossible(Word.getWord()).stream()
			.map(w -> w.getWord())
			.collect(Collectors.toList());
		
		model.addAttribute("wordList", wordList);
		return "/query_results";
	}
}