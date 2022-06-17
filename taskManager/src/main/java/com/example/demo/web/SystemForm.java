package com.example.demo.web;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SystemForm {
	
	 private Integer systemId;
	
	 @NotNull
	 @Size(min = 1, max = 127)
	 private String systemName;
	 
	 @NotNull
	 @Min(1)
	 private Integer languageId;
	 
	 @NotNull
	 @Size(min = 1, max = 127)
	 private String startDate;
	 
	 private String endDate;
}
