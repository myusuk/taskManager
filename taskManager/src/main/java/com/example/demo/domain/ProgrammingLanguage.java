package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.example.demo.domain.System;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "programming_language")
@ToString(exclude = "systems")
public class ProgrammingLanguage {
	
	@Id
	private Integer id;
	private String programLanguage;
	
	@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "languageId")
	private List<System> systems;
	
}
