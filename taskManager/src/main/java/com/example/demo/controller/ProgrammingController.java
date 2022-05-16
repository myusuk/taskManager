package com.example.demo.controller;

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
import com.example.demo.service.SystemService;
import com.example.demo.web.SystemForm;

@Controller
@RequestMapping("programming")
public class ProgrammingController {
	
	@Autowired
	ProgrammingLanguageService programmingLanguageService;
	
	@Autowired
	SystemService systemService;
	
	@ModelAttribute
    SystemForm setUpForm() {
        return new SystemForm();
    }
	
	
	
	@GetMapping
	public String programming(Model model,
			@RequestParam(defaultValue="1") ProgrammingLanguage id){
		List<ProgrammingLanguage> languageList = programmingLanguageService.findAll();
		
		
		List<System> systemList = systemService.findAll();
		Stream<System> stream = systemList.stream();
		stream = stream.filter(m -> m.getLanguageId() == id);
		
		model.addAttribute("languageList", languageList);
		model.addAttribute("systemList", stream.collect(Collectors.toList()));
		
		
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
    String create(@Validated SystemForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            //return programming(model, 1);
        }
        System system = new System();
        BeanUtils.copyProperties(form, system);
        systemService.create(system);
        return "redirect:/programming";
    }
	

}
