package com.example.demo.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.TaskDocument;
import com.example.demo.repository.TaskDocumentRepository;
import com.example.demo.service.TaskDocumentService;
import com.example.demo.web.TaskDocumentForm;

@Service
@Transactional
public class TaskDocumentServiceImpl implements TaskDocumentService {
	
	@Autowired
	TaskDocumentRepository taskDocumentRepository;

	@Override
	public List<TaskDocument> getAll() {
		Iterable<TaskDocument> iteTaskDocument = taskDocumentRepository.findAll();
		List<TaskDocument> documentList = new ArrayList<>();
		iteTaskDocument.forEach(t -> documentList.add(t));
		return documentList;
	}

	@Override
	public TaskDocument getOne(Integer id) {
		return  model2model(taskDocumentRepository.findById(id).get());
	}

	@Override
	public TaskDocument getOneByTaskId(Integer taskId) {
		Optional<TaskDocument> document = taskDocumentRepository.findByTaskId(taskId);
		if(document.isPresent()) {
			return model2model(document.get());
		}
		return null;
	}

	@Override
	public TaskDocument create(TaskDocumentForm form) {
		TaskDocument document = new TaskDocument();
		BeanUtils.copyProperties(form, document);
		return taskDocumentRepository.save(document);
	}

	@Override
	public TaskDocument update(TaskDocumentForm form) {
		TaskDocument document = getOne(form.getDocumentId());
		BeanUtils.copyProperties(form, document);
		return taskDocumentRepository.save(document);
	}

	@Override
	public void delete(Integer id) {
		taskDocumentRepository.deleteById(id);
	}
	
	@Override
	public Boolean getExistsByTaskId(Integer taskId) {
		return taskDocumentRepository.existsByTaskId(taskId);
	}
	
	private TaskDocument model2model(TaskDocument document) {
		TaskDocument t = new TaskDocument();
		t.setId(document.getId());
		t.setPurpose(document.getPurpose());
		t.setFunction(document.getFunction());
		t.setItem(document.getItem());
		t.setPeriod(document.getPeriod());
		t.setTaskId(document.getTaskId());
		return t;
	}

}
