package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.TaskCategory;
import com.example.demo.repository.TaskCategoryRepository;
import com.example.demo.service.TaskCategoryService;

@Service
public class TaskCategoryServiceImpl implements TaskCategoryService {
	
	@Autowired
	TaskCategoryRepository taskCategoryRepository;

	@Override
	public List<TaskCategory> getAll() {
		return taskCategoryRepository.findAll();
	}

	@Override
	public TaskCategory getOne(Integer id) {
		return taskCategoryRepository.findById(id).orElseThrow();
	}

	@Override
	public TaskCategory getByName(String name) {
		return taskCategoryRepository.findByName(name);
	}

	@Override
	public Boolean getExistsById(Integer id) {
		return taskCategoryRepository.existsById(id);
	}

	@Override
	public Boolean getExistsByName(String name) {
		return taskCategoryRepository.existsByName(name);
	}

	@Override
	public TaskCategory create(TaskCategory category) {
		return taskCategoryRepository.save(category);
	}

	@Override
	public TaskCategory update(TaskCategory category) {
		return taskCategoryRepository.save(category);
	}

	@Override
	public void delete(Integer id) {
		taskCategoryRepository.deleteById(id);
	}

}
