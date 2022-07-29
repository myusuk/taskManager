package com.example.demo.util;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class DateChange {
	//String->SQLDate translate class
	public Date stringToDate(String strDate) {
		if(strDate.isBlank()) return null;
		Date date = Date.valueOf(strDate);
		return date;
	}
	
}
