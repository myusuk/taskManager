package com.example.demo.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

import com.example.demo.domain.System;

@Data
public class TaskForm {
	
	@NotNull
	@Size(min = 1, max = 127)
	public String featureNumber;
	
	@NotNull
	@Size(min = 1, max = 127)
	public String overview;
	
	@NotNull
	@Size(min = 1, max = 127)
	public String start_date;
	
	@NotNull
	@Size(min = 1, max = 127)
	public System systemId;
	
}
