package com.example.demo.util;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.service.ProgramCategoryService;
import com.example.demo.service.TargetCategoryService;
import com.example.demo.service.TargetDocumentService;
import com.example.demo.service.TargetService;
import com.example.demo.service.TaskCategoryService;
import com.example.demo.service.TaskDocumentService;
import com.example.demo.service.TaskService;

@Component
public class IdValid {
	
	@Autowired
	ProgramCategoryService programCategoryService;
	@Autowired
	TargetCategoryService targetCategoryService;
	@Autowired
	TaskCategoryService taskCategoryService;
	@Autowired
	TargetService targetService;
	@Autowired
	TaskService taskService;
	@Autowired
	TargetDocumentService targetDocumentService;
	@Autowired
	TaskDocumentService taskDocumentService;
	
	/**
	 *  Id check valid
	 * 
	 */
	
	/**
	 * ProgramCategoryId Valid
	 * 
	 */
	
	public List<String> programCategoryIdValid(Integer categoryId, List<String> message){
		if(Objects.isNull(categoryId)) {
			message.add("Program category is not nullable.");
			return message;
		}
		if(!programCategoryService.getExistsById(categoryId)) {
			message.add("Program category is not found.");
			return message;
		}
		return message;
	}
	
	public List<String> programCategoryIdForTargetValid(Integer categoryId, List<String> message){
		// Null able
		if(Objects.isNull(categoryId)) {
			return message;
		}
		if(!programCategoryService.getExistsById(categoryId)) {
			message.add("Program category is not found.");
			return message;
		}
		return message;
	}
	
	/**
	 * TargetCategoryId Valid
	 * 
	 */
	
	public List<String> targetCategoryIdValid(Integer categoryId, List<String> message){
		if(Objects.isNull(categoryId)) {
			message.add("Target category is not nullable.");
			return message;
		}
		if(!targetCategoryService.getExistsById(categoryId)) {
			message.add("Target category is not found.");
			return message;
		}
		return message;
	}
	
	public List<String> targetCategoryIdForTargetValid(Integer categoryId, List<String> message){
		if(Objects.isNull(categoryId)) {
			message.add("Target category is null.");
			return message;
		}
		if(categoryId.equals(0)) {
			message.add("Target category is required.");
			return message;
		}
		if(!targetCategoryService.getExistsById(categoryId)) {
			message.add("Target category is not found.");
			return message;
		}
		return message;
	}
	
	/**
	 * TaskCategoryId Valid
	 * 
	 */
	
	public List<String> taskCategoryIdValid(Integer categoryId, List<String> message){
		if(Objects.isNull(categoryId)) {
			message.add("Task category is not nullable.");
			return message;
		}
		if(!taskCategoryService.getExistsById(categoryId)) {
			message.add("Task category is not found.");
			return message;
		}
		return message;
	}
	
	public List<String> taskCategoryIdForTaskValid(Integer categoryId, List<String> message){
		if(Objects.isNull(categoryId)) {
			message.add("Task category is null.");
			return message;
		}
		if(categoryId.equals(0)) {
			message.add("Task category is required.");
			return message;
		}
		if(!taskCategoryService.getExistsById(categoryId)) {
			message.add("Task category is not found.");
			return message;
		}
		return message;
	}
 	
	/**
	 *  TargetId Valid
	 * 
	 */
	
	public List<String> targetIdValid(Integer targetId, List<String> message){
		if(Objects.isNull(targetId)) {
			message.add("Target is not nullable.");
			return message;
		}
		if(!targetService.getExistsById(targetId)) {
			message.add("Target is not found.");
			return message;
		}
		return message;
	}
	
	public List<String> targetIdForTaskValid(Integer targetId, List<String> message){
		if(Objects.isNull(targetId)) {
			message.add("Target is not nullable.");
			return message;
		}
		if(targetId.equals(0)) {
			message.add("Target is required.");
			return message;
		}
		if(!targetService.getExistsById(targetId)) {
			message.add("Target is not found.");
			return message;
		}
		return message;
	}
	
	public List<String> targetIdForDocumentValid(Integer targetId, List<String> message){
		if(Objects.isNull(targetId)) {
			message.add("Target is not nullable.");
			return message;
		}
		if(!targetService.getExistsById(targetId)) {
			message.add("Target is not found.");
			return message;
		}
		if(targetDocumentService.getExistsByTargetId(targetId)) {
			message.add("Document is duplicated.");
			return message;
		}
		return message;
	}
	
	/**
	 *  TargetDocumentId Valid
	 * 
	 */
	
	public List<String> targetDocumentIdValid(Integer documentId, List<String> message){
		if(Objects.isNull(documentId)) {
			message.add("Document is not found.");
			return message;
		}
		if(!targetDocumentService.getExistsById(documentId)) {
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
