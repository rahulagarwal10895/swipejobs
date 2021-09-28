package com.swipejobs.matchjob.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.swipejobs.matchjob.filter.JobFilter;
import com.swipejobs.matchjob.model.Job;
import com.swipejobs.matchjob.model.Worker;

@Service("licenceFilter")
public class DrivingLicenceFilter implements JobFilter {

	@Override
	public List<Job> filter(List<Job> allAvailableJobs, Worker worker) {
		if(!worker.isHasDriversLicense())
			return allAvailableJobs.stream().filter(x->!x.isDriverLicenseRequired()).collect(Collectors.toList());
		return allAvailableJobs;
	}
	

}
