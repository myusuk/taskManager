package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.ProgramCategory;
import com.example.demo.repository.ProgramCategoryRepository;
import com.example.demo.service.ProgramCategoryService;

@Service
public class ProgramCategoryServiceImpl implements ProgramCategoryService {
	
	@Autowired
	ProgramCategoryRepository programCategoryRepository;

	@Override
	public List<ProgramCategory> getAll() {
		return programCategoryRepository.findAll();
	}

	@Override
	public ProgramCategory getOne(Integer id) {
		return programCategoryRepository.findById(id).orElseThrow();
	}
	
	@Override
	public ProgramCategory getByName(String name) {
		return programCategoryRepository.findByName(name);
	}
	
	@Override
	public Boolean getExistsById(Integer id) {
		return programCategoryRepository.existsById(id);
	}
	
	@Override
	public Boolean getExistsByName(String name) {
		return programCategoryRepository.existsByName(name);
	}

	@Override
	public ProgramCategory create(ProgramCategory category) {
		return programCategoryRepository.save(category);
	}

	@Override
	public ProgramCategory update(ProgramCategory category) {
		return programCategoryRepository.save(category);
	}

	@Override
	public void delete(Integer id) {
		programCategoryRepository.deleteById(id);
	}

}
