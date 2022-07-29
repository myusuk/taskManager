package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Language;

public interface LanguageService {
	
	public List<Language> getAll();
	
    public Language getOne(Integer id);
    
    public Language getByName(String name);
    
    public Boolean getExistsById(Integer id);
    
    public Boolean getExistsByName(String name);

    public Language create(Language language);

    public Language update(Language language);

    public void delete(Integer id);

}
