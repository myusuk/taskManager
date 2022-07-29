package com.example.demo.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.service.LanguageService;

@Component
public class StringDuplicateValid {
	
	/**
	 *  String Duplicate check validation
	 * 
	 */
	
	@Autowired
	LanguageService languageService;
	
	public List<String> languageNameValid(String name, List<String> message){
		if(name.isBlank()) {
			return message;
		}
		if(languageService.getExistsByName(name)) {
			message.add(name.toString() + " is registerd.");
			return message;
		}
		return message;
		
	}

}
