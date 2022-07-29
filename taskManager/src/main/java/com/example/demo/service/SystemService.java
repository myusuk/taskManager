package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.System;

public interface SystemService {
	
	public List<System> getAll();

    public System getOne(Integer id);
    
    public System getByName(String name);
    
    public Boolean getExistsById(Integer id);
    
    public Boolean getExistsByName(String name);

    public System create(System system);

    public System update(System system);

    public void delete(Integer id);
    
}
