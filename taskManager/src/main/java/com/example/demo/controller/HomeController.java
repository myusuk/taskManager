package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.ProgramCategoryService;
import com.example.demo.service.TargetCategoryService;
import com.example.demo.service.TaskCategoryService;
import com.example.demo.service.TaskService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	TaskService taskService;
	@Autowired
	ProgramCategoryService programCategoryService;
	@Autowired
	TargetCategoryService targetCategoryService;
	@Autowired
	TaskCategoryService taskCategoryService;
	
	@GetMapping
	public String index(Model model) {
		model.addAttribute("programCategoryList", programCategoryService.getAll());
		model.addAttribute("targetCategoryList", targetCategoryService.getAll());
		model.addAttribute("taskCategoryList", taskCategoryService.getAll());
		model.addAttribute("taskList", taskService.getInProcessList());
		return "index";
	}
	
}
