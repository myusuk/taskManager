package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.SystemDocument;

public interface SystemDocumentRepository extends CrudRepository<SystemDocument, Integer>{
	
	SystemDocument findBySystemId(Integer systemId);
	
	Boolean existsBySystemId(Integer systemId);

}
