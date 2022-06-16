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

import com.example.demo.domain.Language;
import com.example.demo.domain.System;
import com.example.demo.service.LanguageService;
import com.example.demo.service.SystemService;
import com.example.demo.web.SystemForm;

@Controller
@RequestMapping("system")
public class SystemController {
	
	@Autowired
	SystemService systemService;
	@Autowired
	LanguageService languageService;
	
	@GetMapping
	public String index(Model model) {
		List<System> systemList = systemService.getAll();
		List<Language>languageList = languageService.getAll();
		model.addAttribute("systemList", systemList);
		model.addAttribute("languageList", languageList);
		
		if(!model.containsAttribute("systemForm")) {
			model.addAttribute("systemForm", new SystemForm());
		}
		return "system/index";
	}
	
	@RequestMapping(path = "create", method = RequestMethod.GET)
    public String create(@Validated SystemForm form, BindingResult result,
    		RedirectAttributes redirectAttributes)  {
		 if (result.hasErrors()) {
			 redirectAttributes.addFlashAttribute("error", result.getAllErrors());
			 return "redirect:/system";
		}
		systemService.create(form);
		return "redirect:/system";
    }
	
	@RequestMapping(path = "edit", method = RequestMethod.GET)
	public String edit(@Validated SystemForm form, BindingResult result,
			RedirectAttributes redirectAttributes) {
		 if (result.hasErrors()) {
			 redirectAttributes.addFlashAttribute("error", result.getAllErrors());
			 return "redirect:/system";
		}
		systemService.update(form);
		return "redirect:/system";
	}
	
	@RequestMapping(path = "delete", method = RequestMethod.GET)
	public String delete(@Validated SystemForm form, BindingResult result,
			RedirectAttributes redirectAttributes) {
		 if (result.hasErrors()) {
			 redirectAttributes.addFlashAttribute("error", result.getAllErrors());
			 return "redirect:/system";
		}
		systemService.delete(form.getSystemId());
		return "redirect:/system";
	}
	
	
	@ResponseBody
	@GetMapping(path = "api/get-one/{id}")
	public System getOne(@PathVariable(value = "id") Integer id) {
		return systemService.getOne(id);
	}

}