package com.swipejobs.matchjob.filter;

import java.util.List;

import com.swipejobs.matchjob.model.Job;
import com.swipejobs.matchjob.model.Worker;

public interface JobFilter {
	
	public List<Job> filter(List<Job> allAvailableJobs, Worker worker);

}
