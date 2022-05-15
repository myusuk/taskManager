package com.example.demo.domain;

import java.util.List;

import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "system")
public class System {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String systemName;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "language_id")
    private ProgrammingLanguage languageId;
}




