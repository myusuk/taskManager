package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.SystemDocument;
import com.example.demo.web.SystemDocumentForm;

public interface SystemDocumentService {
	
	public List<SystemDocument> getAll();

    public SystemDocument getOne(Integer id);
    
    public SystemDocument getOneBySystemId(Integer id);

    public SystemDocument create(SystemDocumentForm form);

    public SystemDocument update(SystemDocumentForm form);

    public void delete(Integer id);
    
    public Boolean getExistsBySystemId(Integer systemId);

}
