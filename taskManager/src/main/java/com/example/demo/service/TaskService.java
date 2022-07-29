package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Task;

public interface TaskService {
	
	public List<Task> getAll();

    public Task getOne(Integer id); 
    
    public Task getOneWithLanguage(Integer id);
    
    public List<Task> getBySystemId(Integer systemId);
    
    public Boolean getExistsById(Integer id);

    public Task create(Task task) ;
    	
    public Task update(Task task);

    public void delete(Integer id);
    
    public List<Task> getInProcessList();

}
