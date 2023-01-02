package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Target;
import com.example.demo.instructure.repository.TargetRepositoryCustom;


public interface TargetRepository extends CrudRepository<Target, Integer> , TargetRepositoryCustom{
	
	List<Target> findAll();
	
	Target findByName(String name);
	
	Boolean existsByName(String name);
	
	List<Target> getAlla();
	
	
}