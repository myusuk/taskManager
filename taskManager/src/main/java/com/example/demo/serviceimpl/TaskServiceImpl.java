package com.example.demo.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import com.example.demo.util.DateChange;
import com.example.demo.web.TaskForm;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	TaskRepository taskRepository;
	
	//string -> SQLDate
	DateChange datechange = new DateChange();

	@Override
	public List<Task> getAll() {
		Iterable<Task> iteTask = taskRepository.findAll();
		List<Task> taskList = new ArrayList<>();
		iteTask.forEach(s -> taskList.add(s));
		return taskList;
	}

	@Override
	public Task getOne(Integer id) {
		return model2model(taskRepository.findById(id).get());
	}

	@Override
	public Task create(TaskForm form) {
		Task task = new Task();
   		BeanUtils.copyProperties(form, task);
		task.setStartDate(datechange.stringToDate(form.getStartDate()));
		return taskRepository.save(task);
	}

	@Override
	public Task update(TaskForm form) {
		Task task = getOne(form.getTaskId());
		BeanUtils.copyProperties(form, task);
		task.setStartDate(datechange.stringToDate(form.getStartDate()));
		if(Objects.nonNull(form.getEndDate())) {
			task.setEndDate(datechange.stringToDate(form.getEndDate()));
		}else {
			task.setEndDate(null);
		}
		return taskRepository.save(task);
	}

	@Override
	public void delete(Integer id) {
		taskRepository.deleteById(id);
	}
	
	@Override
	public List<Task> getProcessList(){
		List<Task> taskList = new ArrayList<>();
		Date today = new Date();
		List<Task> taskAllList = getAll();
		for(Task t: taskAllList) {
			if(today.after(t.getStartDate())){
				if(Objects.isNull(t.getEndDate()) || today.before(t.getEndDate())) {
					taskList.add(t);
				}
			}
		}
		return taskList;
	}
	
	private Task model2model(Task task) {
		Task t = new Task();
		t.setId(task.getId());
		t.setFeatureNumber(task.getFeatureNumber());
		t.setOverview(task.getOverview());
		t.setStartDate(task.getStartDate());
		t.setEndDate(task.getEndDate());
		t.setReport(task.getReport());
		t.setSystemId(task.getSystemId());
		return t;
	}

}
