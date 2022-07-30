package com.example.demo.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "target_document")
public class TargetDocument {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="overview")
	private String overview;
	
	@Column(name="purpose")
	private String purpose;
	
	@Column(name="function")
	private String function;
	
	@Column(name="period")
	private String period;
	
	@Column(name="target_id")
	private Integer targetId;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "target_id", insertable = false, updatable = false)
    private Target target;

}
