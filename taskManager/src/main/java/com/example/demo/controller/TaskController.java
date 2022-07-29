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
import com.example.demo.domain.Task;
import com.example.demo.domain.TaskDocument;
import com.example.demo.service.LanguageService;
import com.example.demo.service.SystemService;
import com.example.demo.service.TaskDocumentService;
import com.example.demo.service.TaskService;
import com.example.demo.util.DateChange;
import com.example.demo.util.DateValid;
import com.example.demo.util.IdValid;
import com.example.demo.util.StringLengthValid;

@Controller
@RequestMapping("task")
public class TaskController {
	
	@Autowired
	TaskService taskService;
	@Autowired
	SystemService systemService;
	@Autowired
	LanguageService languageService;
	@Autowired
	TaskDocumentService taskDocumentService;
	@Autowired
	DateChange dateChange;
	@Autowired
	IdValid idValid;
	@Autowired
	DateValid dateValid;
	@Autowired
	StringLengthValid stringLengthValid;
	
	/**
	 * Task Management
	 * 
	 */
	
	@GetMapping
	public String index(Model model
			,@RequestParam(defaultValue="0") Integer languageId) {
		List<Task> taskList = taskService.getAll();
		//languageId == 0: not filter
		if(!languageId.equals(0)) {
			taskList = taskList.stream()
					.filter(t -> t.getSystem().getLanguageId().equals(languageId))
					.collect(Collectors.toList());
		}
		List<System> systemList = systemService.getAll();
		List<Language> languageList = languageService.getAll();
		
		model.addAttribute("languageId", languageId);
		model.addAttribute("taskList", taskList);
		model.addAttribute("systemList", systemList);
		model.addAttribute("languageList", languageList); 
		return "task/index";
	}
	
	//create
	@ResponseBody
	@RequestMapping(path = "/api/create", method = RequestMethod.POST)
	public ResponseEntity<?> create(String overview, String startDate, 
			String endDate, String report, Integer systemId) {
		Map<String, List<String>> response = new HashMap<>();
		List<String> message = new ArrayList<>();
		//data check
		message = idValid.systemIdForTaskValid(systemId, message);
		message = stringLengthValid.lengthValid(overview, "Overview", 30, false, message);
		message = dateValid.dateValid(startDate, "Start date", false, message);
		message = dateValid.dateValid(endDate, "End date", true, message);
		message = stringLengthValid.lengthValid(report, "Report", 121, true, message);
		if(!message.isEmpty()) {
			response.put("message", message);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		//create
		Task task = new Task();
		task.setOverview(overview);
		task.setStartDate(dateChange.stringToDate(startDate));
		task.setEndDate(dateChange.stringToDate(endDate));
		task.setReport(report);
		task.setSystemId(systemId);
		return new ResponseEntity<>(taskService.create(task), HttpStatus.OK);
	 }
		
	//update
	@ResponseBody
	@RequestMapping(path = "/api/update", method = RequestMethod.POST)
	public ResponseEntity<?> update(Integer id, String overview, String startDate, 
			String endDate, String report, Integer systemId)  {
		Map<String, List<String>> response = new HashMap<>();
		List<String> message = new ArrayList<>();
		//data check
		message = idValid.taskIdValid(id, message);
		message = idValid.systemIdForTaskValid(systemId, message);
		message = stringLengthValid.lengthValid(overview, "Overview", 30, false, message);
		message = dateValid.dateValid(startDate, "Start date", false, message);
		message = dateValid.dateValid(endDate, "End date", true, message);
		message = stringLengthValid.lengthValid(report, "Report", 121, true, message);
		if(!message.isEmpty()) {
			response.put("message", message);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		//update
		Task task = taskService.getOne(id);
		task.setOverview(overview);
		task.setStartDate(dateChange.stringToDate(startDate));
		task.setEndDate(dateChange.stringToDate(endDate));
		task.setReport(report);
		task.setSystemId(systemId);
		return new ResponseEntity<>(taskService.update(task), HttpStatus.OK);
	 }
		
	//delete
	@ResponseBody
	@RequestMapping(path = "/api/delete", method = RequestMethod.POST)
	public ResponseEntity<?> delete(Integer id)  {
		Map<String, List<String>> response = new HashMap<>();
		List<String> message = new ArrayList<>();
		//data check
		message = idValid.taskIdValid(id, message);
		if(!message.isEmpty()) {
			response.put("message", message);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		//delete
		taskService.delete(id);
		return new ResponseEntity<>(new Task(), HttpStatus.OK);
	}
	
	//get task data
	@ResponseBody
	@GetMapping(path = "/api/get-one")
	public Task getOne(Integer id) {
		return taskService.getOne(id);
	}
	
	/**
	 * Task Detail
	 * 
	 */
	

	@GetMapping(path = "{id}")
	public String show(Model model, @PathVariable(value = "id") Integer id) {
		Task task = taskService.getOneWithLanguage(id);
		List<System> systemList = systemService.getAll();
		TaskDocument document = taskDocumentService.getOneByTaskId(id);
		model.addAttribute("task", task);
		model.addAttribute("systemList", systemList);
		model.addAttribute("document", document);
		
		return "task/show";
	}
	
	//create task document
	@ResponseBody
	@RequestMapping(path = "api/document/create", method = RequestMethod.POST)
	public ResponseEntity<?> createDocument(String purpose, String function, 
			String item, String period, Integer taskId) {
		Map<String, List<String>> response = new HashMap<>();
		List<String> message = new ArrayList<>();
		//data check
		message = stringLengthValid.lengthValid(purpose, "Purpose", 255, true, message);
		message = stringLengthValid.lengthValid(function, "Function", 255, true, message);
		message = stringLengthValid.lengthValid(item, "Item", 255, true, message);
		message = stringLengthValid.lengthValid(period, "Period", 30, true, message);
		message = idValid.taskIdForDocumentValid(taskId, message);
		if(!message.isEmpty()) {
			response.put("message", message);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		//create
		TaskDocument document = new TaskDocument();
		document.setPurpose(purpose);
		document.setFunction(function);
		document.setItem(item);
		document.setPeriod(period);
		document.setTaskId(taskId);
		return new ResponseEntity<>(taskDocumentService.create(document), HttpStatus.OK);
	}
		
	//update task document
	@ResponseBody
	@RequestMapping(path = "api/document/update", method = RequestMethod.POST)
	public ResponseEntity<?> updateDocument(Integer id, String purpose, String function,
			String item, String period) {
		Map<String, List<String>> response = new HashMap<>();
		List<String> message = new ArrayList<>();
		//data check
		message = idValid.taskDocumentIdValid(id, message);
		message = stringLengthValid.lengthValid(purpose, "Purpose", 255, true, message);
		message = stringLengthValid.lengthValid(function, "Function", 255, true, message);
		message = stringLengthValid.lengthValid(item, "Item", 255, true, message);
		message = stringLengthValid.lengthValid(period, "Period", 30, true, message);
		if(!message.isEmpty()) {
			response.put("message", message);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		//update
		TaskDocument document = taskDocumentService.getOne(id);
		document.setPurpose(purpose);
		document.setFunction(function);
		document.setItem(item);
		document.setPeriod(period);
		return new ResponseEntity<>(taskDocumentService.update(document), HttpStatus.OK);
	}
	
	//get document data
	@ResponseBody
	@GetMapping(path = "api/document/get-one")
	public TaskDocument getOneDocument(Integer id) {
		return taskDocumentService.getOne(id);
	}
}
