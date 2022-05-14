package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.System;
import com.example.demo.repository.SystemRepository;
@Service
@Transactional
public class SystemService {
	
	@Autowired
	SystemRepository systemRepository;
	
	public List<System> findAll() {
        return systemRepository.findAll();
    }

    public Optional<System> findOne(Integer id) {
        return systemRepository.findById(id);
    }

    public System create(System system) {
        return systemRepository.save(system);
    }

    public System update(System system) {
        return systemRepository.save(system);
    }

    public void delete(Integer id) {
    	systemRepository.deleteById(id);
    }

}
