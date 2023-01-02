package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.ProgramCategory;

public interface ProgramCategoryRepository extends CrudRepository<ProgramCategory, Integer> {
	
	List<ProgramCategory> findAll();
	
	ProgramCategory findByName(String name);
	
	Boolean existsByName(String name);
	
}
