package com.example.demo.util;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class StringLengthValid {
	
	/**
	 *  String length check validation
	 * 
	 */
	
	
	public List<String> lengthValid(String field, String fieldName, Integer length, Boolean nullAble, List<String> message){
		//Null check
		if(field.isBlank()) {
			if(nullAble) {
				return message;
			}else {
				message.add(fieldName + " is not nullable.");
				return message;
			}
		}
		//Length check
		Integer fieldLength = field.length();
		if(fieldLength.compareTo(length) == 1) {
			message.add(fieldName + " enter within " + length.toString() +" characters.");
			return message;
		}
		return message;
	}

}
