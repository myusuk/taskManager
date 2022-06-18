package com.example.demo.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LanguageForm {
	
	private Integer languageId;
	
	@NotNull
	@Size(min = 1, max = 127)
	private String language;

}
