package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.TaskCategory;

public interface TaskCategoryRepository extends CrudRepository<TaskCategory, Integer> {
	
	List<TaskCategory> findAll();
	
	TaskCategory findByName(String name);
	
	Boolean existsByName(String name);

}
