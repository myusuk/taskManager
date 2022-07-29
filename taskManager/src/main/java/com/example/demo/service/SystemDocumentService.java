package com.example.demo.service;

import com.example.demo.domain.SystemDocument;

public interface SystemDocumentService {
	
    public SystemDocument getOne(Integer id);
    
    public SystemDocument getBySystemId(Integer systemId);
    
    public SystemDocument create(SystemDocument document);

    public SystemDocument update(SystemDocument document);
    
    public Boolean getExistsById(Integer id);

    public Boolean getExistsBySystemId(Integer systemId);

}
