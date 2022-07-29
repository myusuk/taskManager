package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Task;

public interface TaskRepository  extends CrudRepository<Task, Integer>{
	
	List<Task> findAll();
	
	@Query("select t from Task t "
			+ "join fetch t.system s ")
	List<Task> findAllWithSystem();
	
	@Query("select t from Task t "
			+ "join fetch t.system s "
			+ "join fetch s.language l "
			+ "where t.id = :id")
	Task findOneWithLanguage(@Param("id") Integer id);
	
	List<Task> findBySystemId(Integer systemId);
	
	@Query("select t from Task t "
			+ "where t.startDate < :date "
			+ "and (t.endDate is null "
			+ "or t.endDate > :date) ")
	List<Task> findInProcessAll(@Param("date") Date date);
	
	
	

}
