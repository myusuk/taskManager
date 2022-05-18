package com.example.demo.web;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.domain.ProgrammingLanguage;

@Data
public class SystemForm {
	
	 @NotNull
	 @Size(min = 1, max = 127)
	 private String systemName;
	 
	 @NotNull
	 @Size(min = 1, max = 127)
	 private ProgrammingLanguage languageId;
	 
	 @NotNull
	 @Size(min = 1, max = 127)
	 private String start_date;
}
