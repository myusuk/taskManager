package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.ProgrammingLanguage;
import com.example.demo.service.ProgrammingLanguageService;
import com.example.demo.domain.System;
import com.example.demo.domain.Task;
import com.example.demo.service.SystemService;
import com.example.demo.service.TaskService;
import com.example.demo.util.DateChenger;
import com.example.demo.web.SystemForm;

@Controller
@RequestMapping("programming")
public class ProgrammingController {
	
	@Autowired
	ProgrammingLanguageService programmingLanguageService;
	
	@Autowired
	SystemService systemService;
	
	@Autowired
	TaskService taskService;
	
	@ModelAttribute
    SystemForm setUpForm() {
        return new SystemForm();
    }
	
	@GetMapping
	public String programming(Model model,
			@RequestParam(defaultValue="1") ProgrammingLanguage id){
		List<ProgrammingLanguage> languageList = programmingLanguageService.findAll();
		
		List<System> systemList = systemService.findAll();
		Stream<System> systemStream = systemList.stream();
		systemStream = systemStream.filter(m -> m.getLanguageId() == id);
		
		List<Task> taskList = taskService.findAll();
		Stream<Task> taskStream = taskList.stream();
		taskStream = taskStream.filter(n -> n.getSystemId().getLanguageId() == id);
		
		
		model.addAttribute("languageList", languageList);
		model.addAttribute("systemList", systemStream.collect(Collectors.toList()));
		model.addAttribute("taskList", taskStream.collect(Collectors.toList()));
		
		return "programming/index";
	}
	
	@GetMapping(path = "edit", params = "form")
    String editForm(@RequestParam Integer id, SystemForm form) {
		Optional<System> system = systemService.findOne(id);
        BeanUtils.copyProperties(system, form);
        return "programming/edit";
    }

    @PostMapping(path = "edit")
    String edit(@RequestParam Integer id, @Validated SystemForm form, BindingResult result) {
        if (result.hasErrors()) {
            return editForm(id, form);
        }
        System system = new System();
        BeanUtils.copyProperties(form, system);
        system.setId(id);
        systemService.update(system);
        return "redirect:/programming";
    }

    @PostMapping(path = "delete")
    String delete(@RequestParam Integer id) {
        systemService.delete(id);
        return "redirect:/programming";
    }
    
    @PostMapping(path = "create")
    String create(@RequestParam String start_date, @Validated SystemForm form, BindingResult result, Model model) throws Exception {
        if (result.hasErrors() | start_date == "") {
            return "redirect:/programming";
        }
        System system = new System();
        Date date = DateChenger.datechanger(start_date);
        BeanUtils.copyProperties(form, system);
        system.setStart_date(date);
        system.setEnd_date(null);
        systemService.create(system);
        return "redirect:/programming";
    }
    
}
