package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.TargetDocument;

public interface TargetDocumentRepository extends CrudRepository<TargetDocument, Integer>{
	
	TargetDocument findByTargetId(Integer targetId);
	
	Boolean existsByTargetId(Integer targetId);

}
