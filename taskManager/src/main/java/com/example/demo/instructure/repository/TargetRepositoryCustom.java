package com.example.demo.instructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


import com.example.demo.domain.Target;

public interface TargetRepositoryCustom {
	
	public List<Target> getAlla();

}
