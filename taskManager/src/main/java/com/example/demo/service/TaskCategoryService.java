package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.TaskCategory;

public interface TaskCategoryService {
	
	public List<TaskCategory> getAll();
	
    public TaskCategory getOne(Integer id);
    
    public TaskCategory getByName(String name);
    
    public Boolean getExistsById(Integer id);
    
    public Boolean getExistsByName(String name);

    public TaskCategory create(TaskCategory category);

    public TaskCategory update(TaskCategory category);

    public void delete(Integer id);


}
