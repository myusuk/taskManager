package com.example.demo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.TaskDocument;
import com.example.demo.repository.TaskDocumentRepository;
import com.example.demo.service.TaskDocumentService;

@Service
public class TaskDocumentServiceImpl implements TaskDocumentService {
	
	@Autowired
	TaskDocumentRepository taskDocumentRepository;

	@Override
	public TaskDocument getOne(Integer id) {
		return  taskDocumentRepository.findById(id).orElseThrow();
	}

	@Override
	public TaskDocument getOneByTaskId(Integer taskId) {
		return taskDocumentRepository.findByTaskId(taskId);
	}
	
	@Override
	public Boolean getExistsById(Integer id) {
		return taskDocumentRepository.existsById(id);
	}
	
	@Override
	public Boolean getExistsByTaskId(Integer taskId) {
		return taskDocumentRepository.existsByTaskId(taskId);
	}

	@Override
	public TaskDocument create(TaskDocument document) {
		return taskDocumentRepository.save(document);
	}

	@Override
	public TaskDocument update(TaskDocument document) {
		return taskDocumentRepository.save(document);
	}

	@Override
	public void delete(Integer id) {
		taskDocumentRepository.deleteById(id);
	}
	
}
