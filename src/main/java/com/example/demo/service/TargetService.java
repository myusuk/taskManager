package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Target;

public interface TargetService {
	
	public List<Target> getAll();

    public Target getOne(Integer id);
    
    public Target getByName(String name);
    
    public Boolean getExistsById(Integer id);
    
    public Boolean getExistsByName(String name);

    public Target create(Target target);

    public Target update(Target target);

    public void delete(Integer id);
    
}
