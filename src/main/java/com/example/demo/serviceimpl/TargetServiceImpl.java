package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Target;
import com.example.demo.repository.TargetRepository;
import com.example.demo.service.TargetService;

@Service
public class TargetServiceImpl implements TargetService {
	
	@Autowired
	TargetRepository targetRepository;
	
	@Override
	public List<Target> getAll() {
		return targetRepository.getAlla();
	}

	@Override
	public Target getOne(Integer id) {
		return targetRepository.findById(id).orElseThrow();
	}
	
	@Override
	public Target getByName(String name) {
		return targetRepository.findByName(name);
	}
	
	@Override
	public Boolean getExistsById(Integer id) {
		return targetRepository.existsById(id);
	}
	
	@Override
	public Boolean getExistsByName(String name) {
		return targetRepository.existsByName(name);
	}

	@Override
	public Target create(Target target) {
		return targetRepository.save(target);
	}

	@Override
	public Target update(Target target) {
		return targetRepository.save(target);
	}

	@Override
	public void delete(Integer id) {
		targetRepository.deleteById(id);
	}
	
}
