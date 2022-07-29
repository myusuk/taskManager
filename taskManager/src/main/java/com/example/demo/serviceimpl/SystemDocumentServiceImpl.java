package com.example.demo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.SystemDocument;
import com.example.demo.repository.SystemDocumentRepository;
import com.example.demo.service.SystemDocumentService;

@Service
public class SystemDocumentServiceImpl implements SystemDocumentService {
	
	@Autowired
	SystemDocumentRepository systemDocumentRepository;

	@Override
	public SystemDocument getOne(Integer id) {
		return systemDocumentRepository.findById(id).orElseThrow();
	}
	
	@Override
	public SystemDocument getBySystemId(Integer systemId) {
		return systemDocumentRepository.findBySystemId(systemId);
	}
	
	@Override
	public SystemDocument create(SystemDocument document) {
		return systemDocumentRepository.save(document);
	}

	@Override
	public SystemDocument update(SystemDocument document) {
		return systemDocumentRepository.save(document);
	}
	
	@Override
	 public Boolean getExistsById(Integer id) {
		return systemDocumentRepository.existsById(id);
	}

	@Override
	public Boolean getExistsBySystemId(Integer systemId) {
		return systemDocumentRepository.existsBySystemId(systemId);
	}
	
}
