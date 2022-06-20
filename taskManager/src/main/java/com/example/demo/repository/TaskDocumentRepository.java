package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.TaskDocument;

public interface TaskDocumentRepository extends CrudRepository<TaskDocument, Integer> {
	
	public Optional<TaskDocument> findByTaskId(Integer taskId);
	
	public Boolean existsByTaskId(Integer taskId);

}
