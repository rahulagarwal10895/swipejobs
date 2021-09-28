package com.swipejobs.matchjob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swipejobs.matchjob.model.Worker;
import com.swipejobs.matchjob.repository.WorkerRepository;

@Service
public class WorkerService {
	
	
	@Autowired
	private WorkerRepository workerRepository;
	
	
	public void loadWorkers(){
		workerRepository.loadWorkers();
	}
	
	public List<Worker> getAllWorkers(){
		return workerRepository.getAllWorkers();
	}
	
	public Worker getWorkerById(int userId){
		return workerRepository.getWorkerByUserId(userId);
	}

}
