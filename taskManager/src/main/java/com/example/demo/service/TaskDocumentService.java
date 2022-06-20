package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.TaskDocument;
import com.example.demo.web.TaskDocumentForm;

public interface TaskDocumentService {
	
	public List<TaskDocument> getAll();

    public TaskDocument getOne(Integer id);
    
    public TaskDocument getOneByTaskId(Integer taskId);

    public TaskDocument create(TaskDocumentForm form);

    public TaskDocument update(TaskDocumentForm form);

    public void delete(Integer id);
    
    public Boolean getExistsByTaskId(Integer taskId);

}
