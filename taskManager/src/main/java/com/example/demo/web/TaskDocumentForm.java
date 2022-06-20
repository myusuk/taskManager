package com.example.demo.web;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TaskDocumentForm {
	
	private Integer documentId;
	
	@NotNull
	@Size(min = 1, max = 255)
	private String purpose;
	
	@NotNull
	@Size(min = 1, max = 255)
	private String function;
	
	@NotNull
	@Size(min = 1, max = 255)
	private String item;
	
	@NotNull
	@Size(min = 1, max = 255)
	private String period;
	
	@NotNull
	@Min(1)
	private Integer taskId;

}
