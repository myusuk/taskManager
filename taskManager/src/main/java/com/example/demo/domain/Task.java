package com.example.demo.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "task")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="report")
	private String report;
	
	@Column(name="target_id")
	private Integer targetId;
	
	@Column(name="task_category_id")
	private Integer taskCategoryId;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "target_id", insertable = false, updatable = false)
    private Target target;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "task_category_id",  insertable = false, updatable = false)
    private TaskCategory taskCategory;
	
	@JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "task")
	private TaskDocument taskDocument;
	
}
