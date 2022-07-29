package com.example.demo.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DateValid {
	
	/**
	 * Date check valid
	 * 
	 */
	
	@Autowired
	DateChange dateChange;
	
	public List<String> dateValid(String field, String fieldName, Boolean nullAble, List<String> message){
		//Null check
		if(field.isBlank()) {
			if(nullAble) {
				return message;
			}else {
				message.add(fieldName.toString() + " is not nullable.");
				return message;
			}
		}
		// illegal value check
		try {
			dateChange.stringToDate(field);
		}catch(IllegalArgumentException e) {
			message.add(fieldName.toString() + " is illegal value.");
		}
		return message;
	}

}
