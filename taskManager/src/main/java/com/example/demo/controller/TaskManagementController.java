package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.System;
import com.example.demo.domain.Task;
import com.example.demo.service.SystemService;
import com.example.demo.service.TaskService;
import com.example.demo.util.DateChenger;
import com.example.demo.web.SystemForm;
import com.example.demo.web.TaskForm;

@Controller
@RequestMapping("task-management")
public class TaskManagementController {
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	SystemService systemService;
	
	@GetMapping
	public String programming(Model model) {
		List<Task> taskList = taskService.findAll();
		List<System> systemList = systemService.findAll();
		model.addAttribute("taskList", taskList);
		model.addAttribute("systemList", systemList);
		
		model.addAttribute("taskForm", new TaskForm());
		return "task-management/index";
	}
	
	@PostMapping(path = "")
    String create(@RequestParam String start_date, 
    		@RequestParam String systemId, @Validated TaskForm form, BindingResult result, Model model) throws Exception {
		if (result.hasErrors() | start_date == "" | systemId == "error") {
            return "redirect:/task-management";
        }
        Task task = new Task();
        Date date = DateChenger.datechanger(start_date);
        BeanUtils.copyProperties(form, task);
        task.setStart_date(date);
        task.setEnd_date(null);
        task.setReport(null);
        taskService.create(task);
        return "redirect:/task-management";
    }

}
