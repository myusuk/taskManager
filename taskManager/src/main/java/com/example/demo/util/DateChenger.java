package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateChenger {
	//String -> Date
	public static Date datechanger(String s) throws Exception{
    	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = f.parse(s);
    	return date;
    }

}
