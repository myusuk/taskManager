package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.TargetCategory;

public interface TargetCategoryService {
	
	public List<TargetCategory> getAll();
	
    public TargetCategory getOne(Integer id);
    
    public TargetCategory getByName(String name);
    
    public Boolean getExistsById(Integer id);
    
    public Boolean getExistsByName(String name);

    public TargetCategory create(TargetCategory category);

    public TargetCategory update(TargetCategory category);

    public void delete(Integer id);


}
