package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.System;
import com.example.demo.web.SystemForm;

public interface SystemService {
	
	public List<System> getAll();

    public System getOne(Integer id);

    public System create(SystemForm form);

    public System update(SystemForm form);

    public void delete(Integer id);

}
