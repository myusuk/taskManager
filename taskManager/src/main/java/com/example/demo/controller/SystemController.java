package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.Language;
import com.example.demo.domain.System;
import com.example.demo.domain.SystemDocument;
import com.example.demo.domain.Task;
import com.example.demo.service.LanguageService;
import com.example.demo.service.SystemDocumentService;
import com.example.demo.service.SystemService;
import com.example.demo.service.TaskService;

import com.example.demo.util.DateChange;
import com.example.demo.util.DateValid;
import com.example.demo.util.StringLengthValid;
import com.example.demo.util.IdValid;

@Controller
@RequestMapping("system")
public class SystemController {
	
	@Autowired
	SystemService systemService;
	@Autowired
	LanguageService languageService;
	@Autowired
	TaskService taskService;
	@Autowired
	SystemDocumentService systemDocumentService;
	@Autowired
	DateChange dateChange;
	@Autowired
	IdValid idValid;
	@Autowired
	DateValid dateValid;
	@Autowired
	StringLengthValid stringLengthValid;
	
	/**
	 *  System Management
	 */
	
	@GetMapping
	public String index(Model model
			,@RequestParam(defaultValue="0") Integer languageId) {
		List<System> systemList = systemService.getAll();
		//languageId == 0: not filter
		if(!languageId.equals(0)) {
			systemList = systemList.stream()
					.filter(s -> s.getLanguageId().equals(languageId))
					.collect(Collectors.toList());
		}
		List<Language>languageList = languageService.getAll();
		
		model.addAttribute("languageId", languageId);
		model.addAttribute("systemList", systemList);
		model.addAttribute("languageList", languageList);
		return "system/index";
	}
	
	//create
	@ResponseBody
	@RequestMapping(path = "/api/create", method = RequestMethod.POST)
	public ResponseEntity<?> create(String name, String startDate, String endDate, Integer languageId) {
		Map<String, List<String>> response = new HashMap<>();
		List<String> message = new ArrayList<>();
		//data check
		message = stringLengthValid.lengthValid(name, "Name", 30, false, message);
		message = idValid.languageIdForSystemValid(languageId, message);
		message = dateValid.dateValid(startDate, "Start date", false, message);
		message = dateValid.dateValid(endDate, "End date", true, message);
		if(!message.isEmpty()) {
			response.put("message", message);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		//create
		System system = new System();
		system.setName(name);
		system.setStartDate(dateChange.stringToDate(startDate));
		system.setEndDate(dateChange.stringToDate(endDate));
		system.setLanguageId(languageId);
		return new ResponseEntity<>(systemService.create(system), HttpStatus.OK);
	 }
	
	//update
	@ResponseBody
	@RequestMapping(path = "/api/update", method = RequestMethod.POST)
	public ResponseEntity<?> update(Integer id, String name, String startDate, String endDate, Integer languageId)  {
		Map<String, List<String>> response = new HashMap<>();
		List<String> message = new ArrayList<>();
		//data check
		message = idValid.systemIdValid(id, message);
		message = stringLengthValid.lengthValid(name, "Name", 30, false, message);
		message = idValid.languageIdForSystemValid(languageId, message);
		message = dateValid.dateValid(startDate, "Start date", false, message);
		message = dateValid.dateValid(endDate, "End date", true, message);
		if(!message.isEmpty()) {
			response.put("message", message);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		//update
		System system = systemService.getOne(id);
		system.setName(name);
		system.setStartDate(dateChange.stringToDate(startDate));
		system.setEndDate(dateChange.stringToDate(endDate));
		system.setLanguageId(languageId);
		return new ResponseEntity<>(systemService.update(system), HttpStatus.OK);
	 }
	
	//delete
	@ResponseBody
	@RequestMapping(path = "/api/delete", method = RequestMethod.POST)
	public ResponseEntity<?> delete(Integer id)  {
		Map<String, List<String>> response = new HashMap<>();
		List<String> message = new ArrayList<>();
		//data check
		message = idValid.systemIdValid(id, message);
		if(!message.isEmpty()) {
			response.put("message", message);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		//delete
		systemService.delete(id);
		return new ResponseEntity<>(new System(), HttpStatus.OK);
	}
	
	//get system data
	@ResponseBody
	@GetMapping(path = "/api/get-one")
	public System getOne(Integer id) {
		return systemService.getOne(id);
	}
	
	
	/**
	 *  System Detail
	 */
	
	@GetMapping(path = "{id}")
	public String show(Model model, @PathVariable(value = "id") Integer id) {
		System system = getOne(id);
		List<Language> languageList = languageService.getAll();
		List<Task> taskList = taskService.getBySystemId(id);
		SystemDocument document = systemDocumentService.getBySystemId(id);
		model.addAttribute("system", system);
		model.addAttribute("languageList", languageList);
		model.addAttribute("taskList", taskList);
		model.addAttribute("document", document);
		
		return "system/show";
	}
	
	//create system document
	@ResponseBody
	@RequestMapping(path = "api/document/create", method = RequestMethod.POST)
	public ResponseEntity<?> createDocument(String overview, String purpose, 
			String function, String period, Integer systemId) {
		Map<String, List<String>> response = new HashMap<>();
		List<String> message = new ArrayList<>();
		//data check
		message = stringLengthValid.lengthValid(overview, "Overview", 30, true, message);
		message = stringLengthValid.lengthValid(purpose, "Purpose", 255, true, message);
		message = stringLengthValid.lengthValid(function, "Function", 255, true, message);
		message = stringLengthValid.lengthValid(period, "Period", 30, true, message);
		message = idValid.systemIdForDocumentValid(systemId, message);
		if(!message.isEmpty()) {
			response.put("message", message);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		//create
		SystemDocument document = new SystemDocument();
		document.setOverview(overview);
		document.setFunction(function);
		document.setPurpose(purpose);
		document.setPeriod(period);
		document.setSystemId(systemId);
		return new ResponseEntity<>(systemDocumentService.create(document), HttpStatus.OK);
	}
	
	//update system document
	@ResponseBody
	@RequestMapping(path = "api/document/update", method = RequestMethod.POST)
	public ResponseEntity<?> updateDocument(Integer id, String overview, 
			String purpose, String function, String period) {
		Map<String, List<String>> response = new HashMap<>();
		List<String> message = new ArrayList<>();
		//data check
		message = idValid.systemDocumentIdValid(id, message);
		message = stringLengthValid.lengthValid(overview, "Overview", 30, true, message);
		message = stringLengthValid.lengthValid(purpose, "Purpose", 255, true, message);
		message = stringLengthValid.lengthValid(function, "Function", 255, true, message);
		message = stringLengthValid.lengthValid(period, "Period", 30, true, message);
		if(!message.isEmpty()) {
			response.put("message", message);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		//update
		SystemDocument document = systemDocumentService.getOne(id);
		document.setOverview(overview);
		document.setFunction(function);
		document.setPurpose(purpose);
		document.setPeriod(period);
		return new ResponseEntity<>(systemDocumentService.update(document), HttpStatus.OK);
	}
	
	//get system document data
	@ResponseBody
	@GetMapping(path = "api/document/get-one")
	public SystemDocument getOneDocument(Integer id) {
		return systemDocumentService.getOne(id);
	}

}
