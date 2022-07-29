package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.Language;

public interface LanguageRepository extends CrudRepository<Language, Integer> {
	
	List<Language> findAll();
	
	Language findByName(String name);
	
	Boolean existsByName(String name);
	
}
