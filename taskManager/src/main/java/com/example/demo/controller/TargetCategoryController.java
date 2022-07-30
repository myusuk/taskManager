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

import com.example.demo.domain.TargetCategory;
import com.example.demo.service.TargetCategoryService;
import com.example.demo.util.IdValid;
import com.example.demo.util.StringDuplicateValid;
import com.example.demo.util.StringLengthValid;

@Controller
@RequestMapping("target-category")
public class TargetCategoryController {
	
	@Autowired
	TargetCategoryService targetCategoryService;
	@Autowired
	IdValid idValid;
	@Autowired
	StringDuplicateValid stringDuplicateValid;
	@Autowired
	StringLengthValid stringLengthValid;
	
	/**
	 *  Target category Management
	 * 
	 */
	
	
	@GetMapping
	public String index(Model model){
		List<TargetCategory> categoryList =  targetCategoryService.getAll();
		model.addAttribute("categoryList", categoryList);
		return "target-category/index";
	}
	
	//create
	@ResponseBody
	@RequestMapping(path = "/api/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(String name)  {
		Map<String, List<String>> response = new HashMap<>();
		List<String> message = new ArrayList<>();
		//data check
		message = stringLengthValid.lengthValid(name, "Name", 31, false, message);
		message = stringDuplicateValid.targetCategoryNameValid(null, name, message);
		if(!message.isEmpty()) {
			response.put("message", message);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		//create
		TargetCategory category = new TargetCategory();
		category.setName(name);
		return ResponseEntity.ok(targetCategoryService.create(category));
    }
	
	//update
	@ResponseBody
	@RequestMapping(path = "/api/update", method = RequestMethod.POST)
    public ResponseEntity<?> update(Integer id, String name)  {
		Map<String, List<String>> response = new HashMap<>();
		List<String> message = new ArrayList<>();
		//data check
		message = idValid.targetCategoryIdValid(id, message);
		message = stringLengthValid.lengthValid(name, "Name", 31, false, message);
		message = stringDuplicateValid.targetCategoryNameValid(id, name, message);
		if(!message.isEmpty()) {
			response.put("message", message);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		//update
		TargetCategory category = targetCategoryService.getOne(id);
		category.setName(name);
		return ResponseEntity.ok(targetCategoryService.update(category));
    }
	
	//delete
	@ResponseBody
	@RequestMapping(path = "/api/delete", method = RequestMethod.POST)
    public ResponseEntity<?> delete(Integer id)  {
		Map<String, List<String>> response = new HashMap<>();
		List<String> message = new ArrayList<>();
		//data check
		message = idValid.targetCategoryIdValid(id, message);
		if(!message.isEmpty()) {
			response.put("message", message);
			return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		//delete
		targetCategoryService.delete(id);
		return ResponseEntity.ok(new TargetCategory());
    }

}
