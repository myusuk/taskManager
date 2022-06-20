package com.example.demo.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "system_document")
public class SystemDocument {
	
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
	
	@Column(name="system_id")
	private Integer systemId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "system_id", insertable = false, updatable = false)
    private System system;

}
