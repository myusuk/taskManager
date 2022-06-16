package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Task;
import com.example.demo.web.TaskForm;

public interface TaskService {
	
	public List<Task> getAll();

    public Task getOne(Integer id); 

    public Task create(TaskForm form) ;
    	
    public Task update(TaskForm  form);

    public void delete(Integer id);
    
    public List<Task> getProcessList();

}
