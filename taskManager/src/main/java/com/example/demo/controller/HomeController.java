package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.TaskService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	TaskService taskService;
	
	@GetMapping
	public String hello(Model model) {
		model.addAttribute("taskList", taskService.getProcessList());
		return "index";
	}
	
}
