package com.example.demo.instructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.Target;

@Repository
public class TargetRepositoryImpl implements TargetRepositoryCustom {
	
	@PersistenceContext()
	public EntityManager entityManager;
	
	@Override
	public List<Target> getAlla() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Target> query = builder.createQuery(Target.class);
		Root<Target> root = query.from(Target.class);
		query.select(root);
		List<Target> list = (List<Target>) entityManager.createQuery(query).getResultList();
		return list;
	}

}
