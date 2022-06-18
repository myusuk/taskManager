package com.example.demo.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Language;
import com.example.demo.repository.LanguageRepository;
import com.example.demo.service.LanguageService;
import com.example.demo.web.LanguageForm;

@Service
@Transactional
public class LanguageServiceImpl implements LanguageService {
	
	@Autowired
	LanguageRepository languageRepository;

	@Override
	public List<Language> getAll() {
		Iterable<Language> iteLanguage = languageRepository.findAll();
		List<Language> languageList = new ArrayList<>();
		iteLanguage.forEach(l -> languageList.add(l));
		return languageList;
	}

	@Override
	public Language getOne(Integer id) {
		return languageRepository.findById(id).get();
	}

	@Override
	public Language create(LanguageForm form) {
		Language language = new Language();
   		BeanUtils.copyProperties(form, language);
		return languageRepository.save(language);
	}

	@Override
	public Language update(LanguageForm form) {
		Language language = getOne(form.getLanguageId());
   		BeanUtils.copyProperties(form, language);
		return languageRepository.save(language);
	}

	@Override
	public void delete(Integer id) {
		languageRepository.deleteById(id);
	}

}
