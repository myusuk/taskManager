package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.Target;

public interface TargetRepository extends CrudRepository<Target, Integer>{
	
	List<Target> findAll();
	
	Target findByName(String name);
	
	Boolean existsByName(String name);
	
	
}
