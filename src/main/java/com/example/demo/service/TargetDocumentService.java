package com.example.demo.service;

import com.example.demo.domain.TargetDocument;

public interface TargetDocumentService {
	
    public TargetDocument getOne(Integer id);
    
    public TargetDocument getByTargetId(Integer targetId);
    
    public Boolean getExistsById(Integer id);

    public Boolean getExistsByTargetId(Integer targetId);
    
    public TargetDocument create(TargetDocument document);

    public TargetDocument update(TargetDocument document);
    
}
