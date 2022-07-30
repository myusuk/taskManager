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

import com.example.demo.domain.ProgramCategory;
import com.example.demo.service.ProgramCategoryService;
import com.example.demo.util.IdValid;
import com.example.demo.util.StringDuplicateValid;
import com.example.demo.util.StringLengthValid;

@Controller
@RequestMapping("program-category")
public class ProgramCategoryController {
	
	@Autowired
	ProgramCategoryService programCategoryService;
	@Autowired
	IdValid idValid;
	@Autowired
	StringDuplicateValid stringDuplicateValid;
	@Autowired
	StringLengthValid stringLengthValid;
	
	/**
	 *  Program category Management
	 * 
	 */
	
	
	@GetMapping
	public String index(Model model){
		List<ProgramCategory> categoryList =  programCategoryService.getAll();
		model.addAttribute("categoryList", categoryList);
		return "program-category/index";
	}
	
	//create
	@ResponseBody
	@RequestMapping(path = "/api/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(String name)  {
		Map<String, List<String>> response = new HashMap<>();
		List<String> message = new ArrayList<>();
		//data check
		message = stringLengthValid.lengthValid(name, "Name", 31, false, message);
		message = stringDuplicateValid.programCategoryNameValid(null, name, message);
		if(!message.isEmpty()) {
			response.put("message", message);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		//create
		ProgramCategory category = new ProgramCategory();
		category.setName(name);
		return ResponseEntity.ok(programCategoryService.create(category));
    }
	
	//update
	@ResponseBody
	@RequestMapping(path = "/api/update", method = RequestMethod.POST)
    public ResponseEntity<?> update(Integer id, String name)  {
		Map<String, List<String>> response = new HashMap<>();
		List<String> message = new ArrayList<>();
		//data check
		message = idValid.programCategoryIdValid(id, message);
		message = stringLengthValid.lengthValid(name, "Name", 31, false, message);
		message = stringDuplicateValid.programCategoryNameValid(id, name, message);
		if(!message.isEmpty()) {
			response.put("message", message);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		//update
		ProgramCategory category = programCategoryService.getOne(id);
		category.setName(name);
		return ResponseEntity.ok(programCategoryService.update(category));
    }
	
	//delete
	@ResponseBody
	@RequestMapping(path = "/api/delete", method = RequestMethod.POST)
    public ResponseEntity<?> delete(Integer id)  {
		Map<String, List<String>> response = new HashMap<>();
		List<String> message = new ArrayList<>();
		//data check
		message = idValid.programCategoryIdValid(id, message);
		if(!message.isEmpty()) {
			response.put("message", message);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		//delete
		programCategoryService.delete(id);
		return ResponseEntity.ok(new ProgramCategory());
    }
    
}
