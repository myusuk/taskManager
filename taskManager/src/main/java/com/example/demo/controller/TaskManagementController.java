package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Task;
import com.example.demo.service.TaskService;

@Controller
@RequestMapping("task-management")
public class TaskManagementController {
	
	@Autowired
	TaskService taskService;
	
	@GetMapping
	public String programming(Model model) {
		List<Task> taskList = taskService.findAll();
		model.addAttribute("taskList", taskList);
		return "task-management/index";
	}

}
