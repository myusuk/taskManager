package com.example.demo.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.System;
import com.example.demo.repository.SystemRepository;
import com.example.demo.service.SystemService;
import com.example.demo.util.DateChange;
import com.example.demo.web.SystemForm;

@Service
@Transactional
public class SystemServiceImpl implements SystemService {
	
	@Autowired
	SystemRepository systemRepository;
	
	DateChange datechange = new DateChange();

	@Override
	public List<System> getAll() {
		Iterable<System> iteSystem = systemRepository.findAll();
		List<System> systemList = new ArrayList<>();
		iteSystem.forEach(s -> systemList.add(s));
		return systemList;
	}

	@Override
	public System getOne(Integer id) {
		return systemRepository.findById(id).get();
	}

	@Override
	public System create(SystemForm form) {
		System system = new System();
   		BeanUtils.copyProperties(form, system);
		system.setStartDate(datechange.stringToDate(form.getStartDate()));
		return systemRepository.save(system);
	}

	@Override
	public System update(SystemForm form) {
		System system = new System();
   		BeanUtils.copyProperties(form, system);
		system.setStartDate(datechange.stringToDate(form.getStartDate()));
		return systemRepository.save(system);
	}

	@Override
	public void delete(Integer id) {
		systemRepository.deleteById(id);
	}

}