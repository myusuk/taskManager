package com.example.demo.util;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.service.ProgramCategoryService;
import com.example.demo.service.TargetCategoryService;
import com.example.demo.service.TaskCategoryService;

@Component
public class StringDuplicateValid {
	
	/**
	 *  String Duplicate check validation
	 * 
	 */
	
	@Autowired
	ProgramCategoryService programCategoryService;
	@Autowired
	TargetCategoryService targetCategoryService;
	@Autowired
	TaskCategoryService taskCategoryService;
	
	/**
	 * Program Category Name Valid
	 * 
	 * @param name
	 * @param message
	 * @return message
	 */
	public List<String> programCategoryNameValid(Integer id, String name, List<String> message){
		if(name.isBlank()) {
			return message;
		}
		if(programCategoryService.getExistsByName(name)) {
			if(Objects.nonNull(id)) {
				if(id.equals(programCategoryService.getByName(name).getId())) {
					return message;
				}
			}
			message.add(name.toString() + " is registerd.");
			return message;
		}
		return message;
	}
	
	/**
	 * Target Category Name Valid
	 * 
	 * @param name
	 * @param message
	 * @return message
	 */
	public List<String> targetCategoryNameValid(Integer id,String name, List<String> message){
		if(name.isBlank()) {
			return message;
		}
		if(targetCategoryService.getExistsByName(name)) {
			if(Objects.nonNull(id)) {
				if(id.equals(targetCategoryService.getByName(name).getId())) {
					return message;
				}
			}
			message.add(name.toString() + " is registerd.");
			return message;
		}
		return message;
	}
	
	/**
	 * Task Category Name Valid
	 * 
	 * @param name
	 * @param message
	 * @return message
	 */
	public List<String> taskCategoryNameValid(Integer id, String name, List<String> message){
		if(name.isBlank()) {
			return message;
		}
		if(taskCategoryService.getExistsByName(name)) {
			if(Objects.nonNull(id)) {
				if(id.equals(taskCategoryService.getByName(name).getId())) {
					return message;
				}
			}
			message.add(name.toString() + " is registerd.");
			return message;
		}
		return message;
	}
	
	
}
