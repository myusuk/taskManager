package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.Language;
import com.example.demo.service.LanguageService;
import com.example.demo.service.SystemService;
import com.example.demo.service.TaskService;
import com.example.demo.web.LanguageForm;
import com.example.demo.web.SystemForm;

@Controller
@RequestMapping("language")
public class LanguageController {
	
	@Autowired
	LanguageService languageService;
	@Autowired
	SystemService systemService;
	@Autowired
	TaskService taskService;
	
	@GetMapping
	public String index(Model model){
		List<Language> languageList = languageService.getAll();
		model.addAttribute("languageList", languageList);
		
		if(!model.containsAttribute("languageForm")) {
			model.addAttribute("languageForm", new LanguageForm());
		}
		return "language/index";
	}
	
	@RequestMapping(path = "create", method = RequestMethod.GET)
    public String create(@Validated LanguageForm form, BindingResult result,
    		RedirectAttributes redirectAttributes)  {
		 if (result.hasErrors()) {
			 redirectAttributes.addFlashAttribute("error", result.getAllErrors());
			 return "redirect:/language";
		}
		languageService.create(form);
		return "redirect:/language";
    }
    
}
