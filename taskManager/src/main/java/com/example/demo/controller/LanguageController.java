package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.Language;
import com.example.demo.service.LanguageService;
import com.example.demo.service.SystemService;
import com.example.demo.service.TaskService;
import com.example.demo.util.IdValid;
import com.example.demo.util.StringDuplicateValid;
import com.example.demo.util.StringLengthValid;

@Controller
@RequestMapping("language")
public class LanguageController {
	
	@Autowired
	LanguageService languageService;
	@Autowired
	SystemService systemService;
	@Autowired
	TaskService taskService;
	@Autowired
	IdValid idValid;
	@Autowired
	StringDuplicateValid stringDuplicateValid;
	
	
	@GetMapping
	public String index(Model model){
		List<Language> languageList = languageService.getAll();
		model.addAttribute("languageList", languageList);
		return "language/index";
	}
	
	//create
	@ResponseBody
	@RequestMapping(path = "/api/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(String name)  {
		Map<String, List<String>> response = new HashMap<>();
		List<String> message = new ArrayList<>();
		//data check
		message = StringLengthValid.lengthValid(name, "Name", 30, false, message);
		message = stringDuplicateValid.languageNameValid(name, message);
		if(!message.isEmpty()) {
			response.put("message", message);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		//create
		Language language = new Language();
		language.setName(name);
		return new ResponseEntity<>(languageService.create(language), HttpStatus.OK);
    }
	
	//update
	@ResponseBody
	@RequestMapping(path = "/api/update", method = RequestMethod.POST)
    public ResponseEntity<?> update(Integer id, String name)  {
		Map<String, List<String>> response = new HashMap<>();
		List<String> message = new ArrayList<>();
		//data check
		message = idValid.languageIdValid(id, message);
		message = StringLengthValid.lengthValid(name, "Name", 30, false, message);
		message = stringDuplicateValid.languageNameValid(name, message);
		if(!message.isEmpty()) {
			response.put("message", message);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		//update
		Language language = languageService.getOne(id);
		language.setName(name);
		return new ResponseEntity<>(languageService.update(language), HttpStatus.OK);
    }
	
	//delete
	@ResponseBody
	@RequestMapping(path = "/api/delete", method = RequestMethod.POST)
    public ResponseEntity<?> delete(Integer id)  {
		Map<String, List<String>> response = new HashMap<>();
		List<String> message = new ArrayList<>();
		//data check
		message = idValid.languageIdValid(id, message);
		if(!message.isEmpty()) {
			response.put("message", message);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		//delete
		languageService.delete(id);
		return new ResponseEntity<>(new Language(), HttpStatus.OK);
    }
    
}
