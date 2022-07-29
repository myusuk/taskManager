package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.System;

public interface SystemRepository extends CrudRepository<System, Integer>{
	
	List<System> findAll();
	
	System findByName(String name);
	
	Boolean existsByName(String name);
	
	
}
