package com.example.demo.web;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TaskForm {
	
	private Integer taskId;
	
	@NotNull
	@Min(1)
	private Integer systemId;
	
	@NotNull
	@Size(min = 1, max = 127)
	private String featureNumber;
	
	@NotNull
	@Size(min = 1, max = 127)
	private String overview;
	
	@NotNull
	@Size(min = 1, max = 127)
	private String startDate;
	
	
	
}
