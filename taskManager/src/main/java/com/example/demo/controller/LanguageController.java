package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.example.demo.domain.Language;
import com.example.demo.service.LanguageService;
import com.example.demo.domain.System;
import com.example.demo.domain.Task;
import com.example.demo.service.SystemService;
import com.example.demo.service.TaskService;
import com.example.demo.web.SystemForm;

@Controller
@RequestMapping("language")
public class LanguageController {
	
	@Autowired
	LanguageService languageService;
	@Autowired
	SystemService systemService;
	@Autowired
	TaskService taskService;
	@ModelAttribute
    SystemForm setUpForm() {
        return new SystemForm();
    }
	
	@GetMapping
	public String index(Model model,
			@RequestParam(defaultValue="1") Integer id){
		List<Language> languageList = languageService.getAll();
		List<System> systemList = systemService.getAll().stream()
				.filter(m -> m.getLanguageId().equals(id)).collect(Collectors.toList());
		List<Task> taskList = taskService.getAll().stream()
				.filter(n -> n.getSystem().getLanguageId().equals(id)).collect(Collectors.toList());
		
		model.addAttribute("languageList", languageList);
		model.addAttribute("systemList", systemList);
		model.addAttribute("taskList", taskList);		
		return "language/index";
	}
	
	
	@GetMapping(path = "edit", params = "form")
    String editForm(@RequestParam Integer id, SystemForm form) {
		System system = systemService.getOne(id);
        BeanUtils.copyProperties(system, form);
        return "language/edit";
    }

    @PostMapping(path = "edit")
    String edit(@Validated SystemForm form, BindingResult result) {
        if (result.hasErrors()) {
            return editForm(form.getLanguageId(), form);
        }
        systemService.update(form);
        return "redirect:/language";
    }

    @PostMapping(path = "delete")
    String delete(@RequestParam Integer id) {
        systemService.delete(id);
        return "redirect:/language";
    }
    
    @PostMapping(path = "create")
    String create(@Validated SystemForm form, BindingResult result, Model model)  {
        if (result.hasErrors()) {
            return "redirect:/language";
        }
        systemService.create(form);
        return "redirect:/language";
    }
    
}
