package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "task_document")
public class TaskDocument {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="purpose")
	private String purpose;
	
	@Column(name="function")
	private String function;
	
	@Column(name="item")
	private String item;
	
	@Column(name="period")
	private String period;
	
	@Column(name="task_id")
	private Integer taskId;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "task_id", insertable = false, updatable = false)
    private Task task;

}