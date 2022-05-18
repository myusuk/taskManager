package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.ProgrammingLanguage;
import com.example.demo.repository.ProgrammingLanguageRepository;

@Service
@Transactional
public class ProgrammingLanguageService {
	
	@Autowired
	ProgrammingLanguageRepository programmingLanguageRepository;
	
	public List<ProgrammingLanguage> findAll() {
        return programmingLanguageRepository.findAll();
    }

    public Optional<ProgrammingLanguage> findOne(Integer id) {
        return programmingLanguageRepository.findById(id);
    }

    public ProgrammingLanguage create(ProgrammingLanguage programmingLanguage) {
        return programmingLanguageRepository.save(programmingLanguage);
    }

    public ProgrammingLanguage update(ProgrammingLanguage programmingLanguage) {
        return programmingLanguageRepository.save(programmingLanguage);
    }

    public void delete(Integer id) {
    	programmingLanguageRepository.deleteById(id);
    }

}
