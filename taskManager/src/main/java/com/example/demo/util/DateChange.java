package com.example.demo.util;

import java.sql.Date;

public class DateChange {
	//String->SQLDate変換用クラス
	public Date stringToDate(String strDate) {
		Date date = Date.valueOf(strDate);
		return date;
	}
	
}
