package com.example.demo.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	TaskRepository taskRepository;
	
	@Override
	public List<Task> getAll() {
		return taskRepository.findAll();
	}
	
	@Override
	public Task getOne(Integer id) {
		return taskRepository.findById(id).orElseThrow();
	}
	
	@Override
	public Task getOneWithLanguage(Integer id) {
		return taskRepository.findOneWithLanguage(id);
	}
	
	@Override
	public List<Task> getBySystemId(Integer systemId){
		return taskRepository.findBySystemId(systemId);
	}
	
	@Override
	public Boolean getExistsById(Integer id) {
		return taskRepository.existsById(id);
	}

	@Override
	public Task create(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public Task update(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public void delete(Integer id) {
		taskRepository.deleteById(id);
	}
	
	@Override
	public List<Task> getInProcessList(){
		return taskRepository.findInProcessAll(new Date());
	}
	
}
