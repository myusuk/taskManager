package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.TargetCategory;
import com.example.demo.repository.TargetCategoryRepository;
import com.example.demo.service.TargetCategoryService;

@Service
public class TargetCategoryServiceImpl implements TargetCategoryService {
	
	@Autowired
	TargetCategoryRepository targetCategoryRepository;

	@Override
	public List<TargetCategory> getAll() {
		return targetCategoryRepository.findAll();
	}

	@Override
	public TargetCategory getOne(Integer id) {
		return targetCategoryRepository.findById(id).orElseThrow();
	}

	@Override
	public TargetCategory getByName(String name) {
		return targetCategoryRepository.findByName(name);
	}

	@Override
	public Boolean getExistsById(Integer id) {
		return targetCategoryRepository.existsById(id);
	}

	@Override
	public Boolean getExistsByName(String name) {
		return targetCategoryRepository.existsByName(name);
	}

	@Override
	public TargetCategory create(TargetCategory category) {
		return targetCategoryRepository.save(category);
	}

	@Override
	public TargetCategory update(TargetCategory category) {
		return targetCategoryRepository.save(category);
	}

	@Override
	public void delete(Integer id) {
		targetCategoryRepository.deleteById(id);;
	}

}
