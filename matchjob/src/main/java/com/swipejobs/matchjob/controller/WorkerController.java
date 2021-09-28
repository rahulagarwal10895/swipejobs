package com.swipejobs.matchjob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swipejobs.matchjob.model.Worker;
import com.swipejobs.matchjob.service.WorkerService;

@RestController
@RequestMapping("/workers")
public class WorkerController {

	@Autowired
	private WorkerService workerService;
	
	
	@GetMapping(value="")
	public List<Worker> getAllWorkers(){
		return workerService.getAllWorkers();
	}
	
	
	@PostMapping(value="")
	public void loadWorkers(){
		workerService.loadWorkers();
	}
	
	@GetMapping(value="{id}")
	public Worker getWorker(@PathVariable("id") Integer userId){
		return workerService.getWorkerById(userId);
	}
	
	
	
	
	
}
