package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.ProgramCategory;

public interface ProgramCategoryService {
	
	public List<ProgramCategory> getAll();
	
    public ProgramCategory getOne(Integer id);
    
    public ProgramCategory getByName(String name);
    
    public Boolean getExistsById(Integer id);
    
    public Boolean getExistsByName(String name);

    public ProgramCategory create(ProgramCategory category);

    public ProgramCategory update(ProgramCategory category);

    public void delete(Integer id);

}
