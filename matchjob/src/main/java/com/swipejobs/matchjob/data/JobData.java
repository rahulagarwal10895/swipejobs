package com.swipejobs.matchjob.data;

import java.util.List;

import com.swipejobs.matchjob.model.Job;

public class JobData {
	
	private static List<Job>  jobs;

	public static List<Job> getJobs() {
		return jobs;
	}

	public static void setJobs(List<Job> jobs) {
		JobData.jobs = jobs;
	}
	
}
