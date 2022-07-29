package com.example.demo.service;

import com.example.demo.domain.TaskDocument;

public interface TaskDocumentService {
	
    public TaskDocument getOne(Integer id);
    
    public TaskDocument getOneByTaskId(Integer taskId);

    public TaskDocument create(TaskDocument document);

    public TaskDocument update(TaskDocument document);

    public void delete(Integer id);
    
    public Boolean getExistsById(Integer id);
    
    public Boolean getExistsByTaskId(Integer taskId);

}
