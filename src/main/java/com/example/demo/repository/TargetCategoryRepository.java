package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.TargetCategory;

public interface TargetCategoryRepository extends CrudRepository<TargetCategory, Integer> {
	
	List<TargetCategory> findAll();
	
	TargetCategory findByName(String name);
	
	Boolean existsByName(String name);

}
