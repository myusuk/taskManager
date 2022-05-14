package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.System;

public interface SystemRepository extends JpaRepository<System, Integer>{
	

}
