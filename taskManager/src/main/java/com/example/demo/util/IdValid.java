package com.example.demo.util;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.service.LanguageService;
import com.example.demo.service.SystemDocumentService;
import com.example.demo.service.SystemService;
import com.example.demo.service.TaskDocumentService;
import com.example.demo.service.TaskService;

@Component
public class IdValid {
	
	@Autowired
	LanguageService languageService;
	@Autowired
	SystemService systemService;
	@Autowired
	TaskService taskService;
	@Autowired
	SystemDocumentService systemDocumentService;
	@Autowired
	TaskDocumentService taskDocumentService;
	
	/**
	 *  Id check valid
	 * 
	 */
	
	/**
	 * LanguageId Valid
	 * 
	 */
	
	public List<String> languageIdValid(Integer languageId, List<String> message){
		if(Objects.isNull(languageId)) {
			message.add("Language is not nullable.");
			return message;
		}
		if(!languageService.getExistsById(languageId)) {
			message.add("Language is not found.");
			return message;
		}
		return message;
	}
	
	public List<String> languageIdForSystemValid(Integer languageId, List<String> message){
		if(Objects.isNull(languageId)) {
			message.add("Language is null.");
			return message;
		}
		if(languageId.equals(0)) {
			message.add("Language is required.");
			return message;
		}
		if(!languageService.getExistsById(languageId)) {
			message.add("Language is not found.");
			return message;
		}
		return message;
	}
 	
	/**
	 *  SystemId Valid
	 * 
	 */
	
	public List<String> systemIdValid(Integer systemId, List<String> message){
		if(Objects.isNull(systemId)) {
			message.add("System is not nullable.");
			return message;
		}
		if(!systemService.getExistsById(systemId)) {
			message.add("System is not found.");
			return message;
		}
		return message;
	}
	
	public List<String> systemIdForTaskValid(Integer systemId, List<String> message){
		if(Objects.isNull(systemId)) {
			message.add("System is not nullable.");
			return message;
		}
		if(systemId.equals(0)) {
			message.add("System is required.");
			return message;
		}
		if(!systemService.getExistsById(systemId)) {
			message.add("System is not found.");
			return message;
		}
		return message;
	}
	
	public List<String> systemIdForDocumentValid(Integer systemId, List<String> message){
		if(Objects.isNull(systemId)) {
			message.add("System is not nullable.");
			return message;
		}
		if(!systemService.getExistsById(systemId)) {
			message.add("System is not found.");
			return message;
		}
		if(systemDocumentService.getExistsBySystemId(systemId)) {
			message.add("Document is duplicated.");
			return message;
		}
		return message;
	}
	
	/**
	 *  SystemDocumentId Valid
	 * 
	 */
	
	public List<String> systemDocumentIdValid(Integer documentId, List<String> message){
		if(Objects.isNull(documentId)) {
			message.add("Document is not found.");
			return message;
		}
		if(!systemDocumentService.getExistsById(documentId)) {
			message.add("Document is not found.");
			return message;
		}
		return message;
	}
	
	/**
	 * TaskId Valid
	 * 
	 */
	
	public List<String> taskIdValid(Integer taskId, List<String> message){
		if(Objects.isNull(taskId)) {
			message.add("Task is not nullable.");
			return message;
		}
		if(!taskService.getExistsById(taskId)) {
			message.add("Task is not found.");
			return message;
		}
		return message;
	}
	
	public List<String> taskIdForDocumentValid(Integer taskId, List<String> message){
		if(Objects.isNull(taskId)) {
			message.add("Task is not nullable.");
			return message;
		}
		if(!taskService.getExistsById(taskId)) {
			message.add("Task is not found.");
			return message;
		}
		if(taskDocumentService.getExistsByTaskId(taskId)) {
			message.add("Document is duplicated.");
			return message;
		}
		return message;
	}
	
	/**
	 *  TaskDocumentId Valid
	 * 
	 */
	
	public List<String> taskDocumentIdValid(Integer documentId, List<String> message){
		if(Objects.isNull(documentId)) {
			message.add("Document is not found.");
			return message;
		}
		if(!taskDocumentService.getExistsById(documentId)) {
			message.add("Document is not found.");
			return message;
		}
		return message;
	}

}
