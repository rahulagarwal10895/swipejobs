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

import com.swipejobs.matchjob.data.JobData;
import com.swipejobs.matchjob.model.Job;

@Repository
public class JobRepository {

	
	@Autowired
	@Qualifier("restTemplate")
	private RestTemplate restTemplate;

	@PostConstruct
	public void loadJobs() {
		ResponseEntity<Job[]> response = restTemplate.getForEntity("https://test.swipejobs.com/api/jobs",
				Job[].class);
		JobData.setJobs(Arrays.asList(response.getBody()));
		System.out.println("Loaded Jobs Successfully");
	}

	public List<Job> getAllJobs() {
		return JobData.getJobs();
	}
	
	public Job getJobByJobId(int jobId){
		return JobData.getJobs().stream().filter(x->x.getJobId()==jobId).collect(Collectors.toList()).get(0);
	}

}
