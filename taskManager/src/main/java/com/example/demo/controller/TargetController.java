package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

import com.example.demo.domain.ProgramCategory;
import com.example.demo.domain.Target;
import com.example.demo.domain.TargetCategory;
import com.example.demo.domain.TargetDocument;
import com.example.demo.domain.Task;
import com.example.demo.service.ProgramCategoryService;
import com.example.demo.service.TargetCategoryService;
import com.example.demo.service.TargetDocumentService;
import com.example.demo.service.TargetService;
import com.example.demo.service.TaskCategoryService;
import com.example.demo.service.TaskService;

import com.example.demo.util.DateChange;
import com.example.demo.util.DateValid;
import com.example.demo.util.StringLengthValid;
import com.example.demo.util.IdValid;

@Controller
@RequestMapping("target")
public class TargetController {
	
	@Autowired
	TargetService targetService;
	@Autowired
	ProgramCategoryService programCategoryService;
	@Autowired
	TargetCategoryService targetCategoryService;
	@Autowired
	TaskCategoryService taskCategoryService;
	@Autowired
	TaskService taskService;
	@Autowired
	TargetDocumentService targetDocumentService;
	@Autowired
	DateChange dateChange;
	@Autowired
	IdValid idValid;
	@Autowired
	DateValid dateValid;
	@Autowired
	StringLengthValid stringLengthValid;
	
	/**
	 *  Target Management
	 */
	
	@GetMapping
	public String index(Model model
			, @RequestParam(defaultValue="0") Integer tc
			, @RequestParam(defaultValue="0") Integer pc) {
		List<Target> targetList = targetService.getAll();
		/**
		 * tc: Target category id
		 * pc: Program category id
		 * if category id is 0. return all target.
		 */
		if(!tc.equals(0)) {
			targetList = targetList.stream()
					.filter(t -> t.getTargetCategoryId().equals(tc))
					.collect(Collectors.toList());
		}
		if(!pc.equals(0)) {
			targetList = targetList.stream()
					.filter(t -> t.getProgramCategory() != null)
					.filter(t -> t.getProgramCategoryId().equals(pc))
					.collect(Collectors.toList());
		}
		
		model.addAttribute("tc", tc);
		model.addAttribute("pc", pc);
		model.addAttribute("targetList", targetList);
		model.addAttribute("programCategoryList", programCategoryService.getAll());
		model.addAttribute("targetCategoryList", targetCategoryService.getAll());
		model.addAttribute("taskCategoryList", taskCategoryService.getAll());
		return "target/index";
	}
	
	//create
	@ResponseBody
	@RequestMapping(path = "/api/create", method = RequestMethod.POST)
	public ResponseEntity<?> create(String name, String startDate, String endDate, 
			Integer programCategoryId, Integer targetCategoryId) {
		Map<String, List<String>> response = new HashMap<>();
		List<String> message = new ArrayList<>();
		//data check
		message = stringLengthValid.lengthValid(name, "Name", 30, false, message);
		message = idValid.targetCategoryIdForTargetValid(targetCategoryId, message);
		message = idValid.programCategoryIdForTargetValid(programCategoryId, message);
		message = dateValid.dateValid(startDate, "Start date", false, message);
		message = dateValid.dateValid(endDate, "End date", true, message);
		if(!message.isEmpty()) {
			response.put("message", message);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		//create
		Target target = new Target();
		target.setName(name);
		target.setStartDate(dateChange.stringToDate(startDate));
		target.setEndDate(dateChange.stringToDate(endDate));
		target.setTargetCategoryId(targetCategoryId);
		target.setProgramCategoryId(programCategoryId);
		return new ResponseEntity<>(targetService.create(target), HttpStatus.OK);
	 }
	
	//update
	@ResponseBody
	@RequestMapping(path = "/api/update", method = RequestMethod.POST)
	public ResponseEntity<?> update(Integer id, String name, String startDate, String endDate,
			Integer programCategoryId, Integer targetCategoryId)  {
		Map<String, List<String>> response = new HashMap<>();
		List<String> message = new ArrayList<>();
		//data check
		message = idValid.targetIdValid(id, message);
		message = stringLengthValid.lengthValid(name, "Name", 30, false, message);
		message = idValid.targetCategoryIdForTargetValid(targetCategoryId, message);
		message = idValid.programCategoryIdForTargetValid(programCategoryId, message);
		message = dateValid.dateValid(startDate, "Start date", false, message);
		message = dateValid.dateValid(endDate, "End date", true, message);
		if(!message.isEmpty()) {
			response.put("message", message);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		//update
		Target target = targetService.getOne(id);
		target.setName(name);
		target.setStartDate(dateChange.stringToDate(startDate));
		target.setEndDate(dateChange.stringToDate(endDate));
		target.setTargetCategoryId(targetCategoryId);
		target.setProgramCategoryId(programCategoryId);
		return new ResponseEntity<>(targetService.update(target), HttpStatus.OK);
	 }
	
	//delete
	@ResponseBody
	@RequestMapping(path = "/api/delete", method = RequestMethod.POST)
	public ResponseEntity<?> delete(Integer id)  {
		Map<String, List<String>> response = new HashMap<>();
		List<String> message = new ArrayList<>();
		//data check
		message = idValid.targetIdValid(id, message);
		if(!message.isEmpty()) {
			response.put("message", message);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		//delete
		targetService.delete(id);
		return new ResponseEntity<>(new Target(), HttpStatus.OK);
	}
	
	//get target data
	@ResponseBody
	@GetMapping(path = "/api/get-one")
	public Target getOne(Integer id) {
		return targetService.getOne(id);
	}
	
	
	/**
	 *  Target Detail
	 */
	
	@GetMapping(path = "{id}")
	public String show(Model model, @PathVariable(value = "id") Integer id) {
		Target target = targetService.getOne(id);
		List<Task> taskList = taskService.getByTargetId(id);
		TargetDocument document = targetDocumentService.getByTargetId(id);
		model.addAttribute("target", target);
		model.addAttribute("taskList", taskList);
		model.addAttribute("programCategoryList", programCategoryService.getAll());
		model.addAttribute("targetCategoryList", targetCategoryService.getAll());
		model.addAttribute("taskCategoryList", taskCategoryService.getAll());
		model.addAttribute("document", document);
		return "target/show";
	}
	
	//create target document
	@ResponseBody
	@RequestMapping(path = "api/document/create", method = RequestMethod.POST)
	public ResponseEntity<?> createDocument(String overview, String purpose, 
			String function, String period, Integer targetId) {
		Map<String, List<String>> response = new HashMap<>();
		List<String> message = new ArrayList<>();
		//data check
		message = stringLengthValid.lengthValid(overview, "Overview", 31, true, message);
		message = stringLengthValid.lengthValid(purpose, "Purpose", 255, true, message);
		message = stringLengthValid.lengthValid(function, "Function", 255, true, message);
		message = stringLengthValid.lengthValid(period, "Period", 31, true, message);
		message = idValid.targetIdForDocumentValid(targetId, message);
		if(!message.isEmpty()) {
			response.put("message", message);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		//create
		TargetDocument document = new TargetDocument();
		document.setOverview(overview);
		document.setFunction(function);
		document.setPurpose(purpose);
		document.setPeriod(period);
		document.setTargetId(targetId);
		return new ResponseEntity<>(targetDocumentService.create(document), HttpStatus.OK);
	}
	
	//update target document
	@ResponseBody
	@RequestMapping(path = "api/document/update", method = RequestMethod.POST)
	public ResponseEntity<?> updateDocument(Integer id, String overview, 
			String purpose, String function, String period) {
		Map<String, List<String>> response = new HashMap<>();
		List<String> message = new ArrayList<>();
		//data check
		message = idValid.targetDocumentIdValid(id, message);
		message = stringLengthValid.lengthValid(overview, "Overview", 30, true, message);
		message = stringLengthValid.lengthValid(purpose, "Purpose", 255, true, message);
		message = stringLengthValid.lengthValid(function, "Function", 255, true, message);
		message = stringLengthValid.lengthValid(period, "Period", 30, true, message);
		if(!message.isEmpty()) {
			response.put("message", message);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		//update
		TargetDocument document = targetDocumentService.getOne(id);
		document.setOverview(overview);
		document.setFunction(function);
		document.setPurpose(purpose);
		document.setPeriod(period);
		return new ResponseEntity<>(targetDocumentService.update(document), HttpStatus.OK);
	}
	
	//get target document data
	@ResponseBody
	@GetMapping(path = "api/document/get-one")
	public TargetDocument getOneDocument(Integer id) {
		return targetDocumentService.getOne(id);
	}

}
