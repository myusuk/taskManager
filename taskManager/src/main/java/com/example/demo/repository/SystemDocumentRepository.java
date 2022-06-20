package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.SystemDocument;

public interface SystemDocumentRepository extends CrudRepository<SystemDocument, Integer>{
	
	public Optional<SystemDocument> findBySystemId(Integer systemId);
	
	public Boolean existsBySystemId(Integer systemId);

}
