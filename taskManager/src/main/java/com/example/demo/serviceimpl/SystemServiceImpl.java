package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.System;
import com.example.demo.repository.SystemRepository;
import com.example.demo.service.SystemService;

@Service
public class SystemServiceImpl implements SystemService {
	
	@Autowired
	SystemRepository systemRepository;
	
	@Override
	public List<System> getAll() {
		return systemRepository.findAll();
	}

	@Override
	public System getOne(Integer id) {
		return systemRepository.findById(id).orElseThrow();
	}
	
	@Override
	public System getByName(String name) {
		return systemRepository.findByName(name);
	}
	
	@Override
	public Boolean getExistsById(Integer id) {
		return systemRepository.existsById(id);
	}
	
	@Override
	public Boolean getExistsByName(String name) {
		return systemRepository.existsByName(name);
	}

	@Override
	public System create(System system) {
		return systemRepository.save(system);
	}

	@Override
	public System update(System system) {
		return systemRepository.save(system);
	}

	@Override
	public void delete(Integer id) {
		systemRepository.deleteById(id);
	}
	
}
