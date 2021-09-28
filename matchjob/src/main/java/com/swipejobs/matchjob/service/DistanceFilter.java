package com.swipejobs.matchjob.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.swipejobs.matchjob.filter.JobFilter;
import com.swipejobs.matchjob.model.Job;
import com.swipejobs.matchjob.model.Worker;

@Service("distanceFilter")
public class DistanceFilter implements JobFilter {

	@Override
	public List<Job> filter(List<Job> allAvailableJobs, Worker worker) {
		return allAvailableJobs.stream().filter(x->x.getDistance()<=worker.getJobSearchAddress().getMaxJobDistance()).collect(Collectors.toList());
	}

}
