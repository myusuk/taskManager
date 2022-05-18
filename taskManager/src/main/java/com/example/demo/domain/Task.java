package com.example.demo.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.demo.domain.System;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String featureNumber;
	
	private String overview;
	
	@Temporal(TemporalType.DATE)
	private Date start_date;
	
	@Temporal(TemporalType.DATE)
	private Date end_date;
	
	private String report;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "system_id")
    private System systemId;
	
}
