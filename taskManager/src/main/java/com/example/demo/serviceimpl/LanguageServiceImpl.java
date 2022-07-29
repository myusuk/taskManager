package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Language;
import com.example.demo.repository.LanguageRepository;
import com.example.demo.service.LanguageService;

@Service
public class LanguageServiceImpl implements LanguageService {
	
	@Autowired
	LanguageRepository languageRepository;

	@Override
	public List<Language> getAll() {
		return languageRepository.findAll();
	}

	@Override
	public Language getOne(Integer id) {
		return languageRepository.findById(id).orElseThrow();
	}
	
	@Override
	public Language getByName(String name) {
		return languageRepository.findByName(name);
	}
	
	@Override
	public Boolean getExistsById(Integer id) {
		return languageRepository.existsById(id);
	}
	
	@Override
	public Boolean getExistsByName(String name) {
		return languageRepository.existsByName(name);
	}

	@Override
	public Language create(Language language) {
		return languageRepository.save(language);
	}

	@Override
	public Language update(Language language) {
		return languageRepository.save(language);
	}

	@Override
	public void delete(Integer id) {
		languageRepository.deleteById(id);
	}

}
