package com.example.demo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.TargetDocument;
import com.example.demo.repository.TargetDocumentRepository;
import com.example.demo.service.TargetDocumentService;

@Service
public class TargetDocumentServiceImpl implements TargetDocumentService {
	
	@Autowired
	TargetDocumentRepository targetDocumentRepository;

	@Override
	public TargetDocument getOne(Integer id) {
		return targetDocumentRepository.findById(id).orElseThrow();
	}
	
	@Override
	public TargetDocument getByTargetId(Integer targetId) {
		return targetDocumentRepository.findByTargetId(targetId);
	}
	
	@Override
	 public Boolean getExistsById(Integer id) {
		return targetDocumentRepository.existsById(id);
	}

	@Override
	public Boolean getExistsByTargetId(Integer targetId) {
		return targetDocumentRepository.existsByTargetId(targetId);
	}
	
	@Override
	public TargetDocument create(TargetDocument document) {
		return targetDocumentRepository.save(document);
	}

	@Override
	public TargetDocument update(TargetDocument document) {
		return targetDocumentRepository.save(document);
	}
	
}
