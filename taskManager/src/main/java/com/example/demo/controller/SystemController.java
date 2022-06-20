package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.Language;
import com.example.demo.domain.System;
import com.example.demo.domain.SystemDocument;
import com.example.demo.domain.Task;
import com.example.demo.service.LanguageService;
import com.example.demo.service.SystemDocumentService;
import com.example.demo.service.SystemService;
import com.example.demo.service.TaskService;
import com.example.demo.web.SystemDocumentForm;
import com.example.demo.web.SystemForm;

@Controller
@RequestMapping("system")
public class SystemController {
	
	@Autowired
	SystemService systemService;
	@Autowired
	LanguageService languageService;
	@Autowired
	TaskService taskService;
	@Autowired
	SystemDocumentService systemDocumentService;
	
	@GetMapping
	public String index(Model model
			,@RequestParam(defaultValue="0") Integer langId) {
		List<System> systemList = systemService.getAll();
		if(!langId.equals(0)) {
			systemList = systemList.stream()
					.filter(s -> s.getLanguageId().equals(langId))
					.collect(Collectors.toList());
		}
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
			 redirectAttributes.addFlashAttribute("formType", "register");
			 redirectAttributes.addFlashAttribute("form", form.getSystemId());
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
			 redirectAttributes.addFlashAttribute("formType", "edit");
			 redirectAttributes.addFlashAttribute("form", form.getSystemId());
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
			 redirectAttributes.addFlashAttribute("formType", "delete");
			 redirectAttributes.addFlashAttribute("form", form.getSystemId());
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
	
	@GetMapping(path = "{id}")
	public String page(Model model, @PathVariable(value = "id") Integer id) {
		System system = getOne(id);
		List<Language> languageList = languageService.getAll();
		List<Task> taskList = taskService.getAll().stream()
				.filter(t -> t.getSystemId().equals(id)).collect(Collectors.toList());
		SystemDocument document = systemDocumentService.getOneBySystemId(id);
		model.addAttribute("system", system);
		model.addAttribute("languageList", languageList);
		model.addAttribute("taskList", taskList);
		model.addAttribute("document", document);
		
		if(!model.containsAttribute("systemForm")) {
			model.addAttribute("systemForm", new SystemForm());
		}
		if(!model.containsAttribute("systemDocumentForm")) {
			model.addAttribute("systemDocumentForm", new SystemDocumentForm());
		}
		return "system/show";
	}
	
	@RequestMapping(path = "document/edit", method = RequestMethod.GET)
	public String editDocument(@Validated SystemDocumentForm form, BindingResult result,
			RedirectAttributes redirectAttributes) {
		 if (result.hasErrors()) {
			 redirectAttributes.addFlashAttribute("docError", result.getAllErrors());
			 redirectAttributes.addFlashAttribute("docForm", form.getSystemId());
			 return "redirect:/system/" + form.getSystemId();
		}
		 if(systemDocumentService.getExistsBySystemId(form.getSystemId())) {
			 systemDocumentService.update(form);
			return "redirect:/system/" + form.getSystemId() ;
		 }
		 systemDocumentService.create(form);
		return "redirect:/system/" + form.getSystemId() ;
	}
	
	@ResponseBody
	@GetMapping(path = "api/document/get-one/{id}")
	public SystemDocument getOneDocument(@PathVariable(value = "id") Integer systemId) {
		return systemDocumentService.getOneBySystemId(systemId);
	}

}
