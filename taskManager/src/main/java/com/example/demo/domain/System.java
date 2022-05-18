package com.example.demo.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.example.demo.domain.System;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.example.demo.domain.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "system")
@ToString(exclude = "tasks")
public class System {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String systemName;
	
	@Temporal(TemporalType.DATE)
	private Date start_date;
	
	@Temporal(TemporalType.DATE)
	private Date end_date;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "language_id")
    private ProgrammingLanguage languageId;
	
	@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "systemId")
	private List<Task> tasks;
	
}




