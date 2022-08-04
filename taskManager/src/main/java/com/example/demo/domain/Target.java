package com.example.demo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.example.demo.domain.Target;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "target")
public class Target {
	
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
	
	@Column(name="target_category_id")
	private Integer targetCategoryId;
	
	@Column(name="program_category_id")
	private Integer programCategoryId;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "target_category_id",  insertable = false, updatable = false)
    private TargetCategory targetCategory;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "program_category_id",  insertable = false, updatable = false)
    private ProgramCategory programCategory;
	
	@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "target")
	private List<Task> task;
	
	@JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "target")
	private TargetDocument targetDocument;
	
	
	private enum Status{
		NOT_START,
		PROCESSING,
		COMPLATE;
		
		public List<Status> getStatusList(){
			List<Status> list = new ArrayList<>();
			for(Status s: Status.values()) {
				list.add(s);
			}
			return list;
		}
	}
	
	private enum Impotance{
		WORK,
		PROJECT,
		SELF_INVESTMENT,
		LEANING,
		HOBBY;
		
		public List<Impotance> getImpotanceList(){
			List<Impotance> list = new ArrayList<>();
			for(Impotance s: Impotance.values()) {
				list.add(s);
			}
			return list;
		}
	}

}




