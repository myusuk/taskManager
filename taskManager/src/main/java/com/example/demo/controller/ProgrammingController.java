package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.ProgrammingLanguage;
import com.example.demo.service.ProgrammingLanguageService;

@Controller
@RequestMapping("programming")
public class ProgrammingController {
	
	@Autowired
	ProgrammingLanguageService programmingLanguageService;
	
	
	
	@GetMapping
	public String programming(Model model) {
		List<ProgrammingLanguage> languageList = programmingLanguageService.findAll();
		model.addAttribute("languageList", languageList);
		
		return "programming/index";
	}
	

}
