package com.example.demo.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.SystemDocument;
import com.example.demo.repository.SystemDocumentRepository;
import com.example.demo.service.SystemDocumentService;
import com.example.demo.web.SystemDocumentForm;

@Service
@Transactional
public class SystemDocumentServiceImpl implements SystemDocumentService {
	
	@Autowired
	SystemDocumentRepository systemDocumentRepository;

	@Override
	public List<SystemDocument> getAll() {
		Iterable<SystemDocument> iteSystemDocument = systemDocumentRepository.findAll();
		List<SystemDocument> documentList = new ArrayList<>();
		iteSystemDocument.forEach(s -> documentList.add(s));
		return documentList;
	}

	@Override
	public SystemDocument getOne(Integer id) {
		return model2model(systemDocumentRepository.findById(id).get());
	}
	
	@Override
	public SystemDocument getOneBySystemId(Integer systemId) {
		Optional<SystemDocument> document = systemDocumentRepository.findBySystemId(systemId);
		if(document.isPresent()) {
			return model2model(document.get());
		}
		return null;
	}

	@Override
	public SystemDocument create(SystemDocumentForm form) {
		SystemDocument document = new SystemDocument();
		BeanUtils.copyProperties(form, document);
		return systemDocumentRepository.save(document);
	}

	@Override
	public SystemDocument update(SystemDocumentForm form) {
		SystemDocument document = getOne(form.getDocumentId());
		BeanUtils.copyProperties(form, document);
		return systemDocumentRepository.save(document);
	}

	@Override
	public void delete(Integer id) {
		systemDocumentRepository.deleteById(id);
	}
	
	@Override
	public Boolean getExistsBySystemId(Integer systemId) {
		return systemDocumentRepository.existsBySystemId(systemId);
	}
	
	private SystemDocument model2model(SystemDocument document) {
		SystemDocument s = new SystemDocument();
		s.setId(document.getId());
		s.setOverview(document.getOverview());
		s.setPurpose(document.getPurpose());
		s.setFunction(document.getFunction());
		s.setPeriod(document.getPeriod());
		s.setSystemId(document.getSystemId());
		return s;
	}

}
