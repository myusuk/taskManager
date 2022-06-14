package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.System;
import com.example.demo.domain.Task;
import com.example.demo.service.SystemService;
import com.example.demo.service.TaskService;
import com.example.demo.web.TaskForm;


@Controller
@RequestMapping("task")
public class TaskController {
	
	@Autowired
	TaskService taskService;
	@Autowired
	SystemService systemService;
	
	@GetMapping
	public String index(Model model) {
		List<Task> taskList = taskService.getAll();
		List<System> systemList = systemService.getAll();
		model.addAttribute("taskList", taskList);
		model.addAttribute("systemList", systemList);
		
		if(!model.containsAttribute("taskForm")) {
			model.addAttribute("taskForm", new TaskForm());
		}
		return "task/index";
	}
	
	@RequestMapping(path = "create", method = RequestMethod.GET)
    public String create(@Validated TaskForm form, BindingResult result,
    		RedirectAttributes redirectAttributes)  {
		 if (result.hasErrors()) {
			 redirectAttributes.addFlashAttribute("error", result.getAllErrors());
			 return "redirect:/task";
		}
		taskService.create(form);
		return "redirect:/task";
    }
	
	@RequestMapping(path = "edit", method = RequestMethod.GET)
	public String edit(@Validated TaskForm form, BindingResult result,
			RedirectAttributes redirectAttributes) {
		 if (result.hasErrors()) {
			 redirectAttributes.addFlashAttribute("error", result.getAllErrors());
			 return "redirect:/task";
		}
		taskService.update(form);
		return "redirect:/task";
	}
	
	@RequestMapping(path = "delete", method = RequestMethod.GET)
	public String delete(@Validated TaskForm form, BindingResult result,
			RedirectAttributes redirectAttributes) {
		 if (result.hasErrors()) {
			 redirectAttributes.addFlashAttribute("error", result.getAllErrors());
			 return "redirect:/task";
		}
		taskService.delete(form.getTaskId());
		return "redirect:/task";
	}
	
	@ResponseBody
	@GetMapping(path = "api/get-one/{id}")
	public Task getOne(@PathVariable(value = "id") Integer id) {
		return taskService.getOne(id);
	}
}
