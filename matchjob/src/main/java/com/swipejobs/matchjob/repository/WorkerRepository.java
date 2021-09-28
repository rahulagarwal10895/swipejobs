package com.swipejobs.matchjob.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.swipejobs.matchjob.data.WorkerData;
import com.swipejobs.matchjob.model.Worker;

@Repository
public class WorkerRepository {

	@Autowired
	@Qualifier("restTemplate")
	private RestTemplate restTemplate;

	@PostConstruct
	public void loadWorkers() {
		ResponseEntity<Worker[]> response = restTemplate.getForEntity("https://test.swipejobs.com/api/workers",
				Worker[].class);
		WorkerData.setWorkers(Arrays.asList(response.getBody()));
		System.out.println("Loaded workers Successfully");
	}

	public List<Worker> getAllWorkers() {
		return WorkerData.getWorkers();
	}
	
	public Worker getWorkerByUserId(int userId){
		return WorkerData.getWorkers().stream().filter(x->x.getUserId()==userId).collect(Collectors.toList()).get(0);
	}

}
