package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Language;
import com.example.demo.web.LanguageForm;

public interface LanguageService {
	
	public List<Language> getAll();

    public Language getOne(Integer id);

    public Language create(LanguageForm form);

    public Language update(LanguageForm form);

    public void delete(Integer id);

}
