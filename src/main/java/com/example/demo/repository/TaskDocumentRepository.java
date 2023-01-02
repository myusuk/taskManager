package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.TaskDocument;

public interface TaskDocumentRepository extends CrudRepository<TaskDocument, Integer> {
	
	TaskDocument findByTaskId(Integer taskId);
	
	Boolean existsByTaskId(Integer taskId);

}
