package com.swipejobs.matchjob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swipejobs.matchjob.model.Job;
import com.swipejobs.matchjob.service.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController {
	
	@Autowired
	private JobService jobService;
	
	
	@GetMapping(value="")
	public List<Job> getAllJobs(){
		return jobService.getAllJobs();
	}
	
	
	@PostMapping(value="")
	public void loadJobs(){
		jobService.loadJobs();
	}
	
	@GetMapping(value="{id}")
	public Job getJob(@PathVariable("id") Integer jobId){
		return jobService.getJobById(jobId);
	} 
	
	@GetMapping(value="/worker/{id}")
	public List<Job> getJobsForWorker(@PathVariable("id") Integer userId){
		return jobService.getMatchingJobsForWorker(userId);
	}

}
