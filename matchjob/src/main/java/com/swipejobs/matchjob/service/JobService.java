package com.swipejobs.matchjob.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.swipejobs.matchjob.filter.JobFilter;
import com.swipejobs.matchjob.model.Job;
import com.swipejobs.matchjob.model.Worker;
import com.swipejobs.matchjob.repository.JobRepository;
import com.swipejobs.matchjob.util.LocationUtil;

@Service
public class JobService {

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private WorkerService workerService;

	@Value("${jobs.matchcount}")
	private Integer jobMatchCount;

	@Value("#{'${jobs.filter}'.split(',')}")
	private List<String> filters;

	@Autowired
	private ApplicationContext context;

	public void loadJobs() {
		jobRepository.loadJobs();
	}

	public List<Job> getAllJobs() {
		return jobRepository.getAllJobs();
	}

	public Job getJobById(int jobId) {
		return jobRepository.getJobByJobId(jobId);
	}

	public List<Job> getMatchingJobsForWorker(int userId) {

		Worker worker = workerService.getWorkerById(userId);
		List<Job> allAvailableJobs = getAllJobs();

		// set distance of job
		setDistance(allAvailableJobs, worker);

		// set relevance according to matching certificates
		setRelevance(allAvailableJobs, worker);

		// filter on licence and distance
		filterJobs(allAvailableJobs, worker);

		return getTopMatchingJobs(allAvailableJobs);
	}

	private void setDistance(List<Job> allAvailableJobs, Worker worker) {
		allAvailableJobs.forEach(
				job -> job.setDistance(LocationUtil.getDistance(job.getLocation(), worker.getJobSearchAddress())));
	}

	private void setRelevance(List<Job> allAvailableJobs, Worker worker) {
		allAvailableJobs.forEach(job -> {
			double count = job.getRequiredCertificates().stream()
					.filter(certificate -> worker.getCertificates().contains(certificate)).count();
			job.setRelevance(count / job.getRequiredCertificates().size());
		});
	}
	
	private void filterJobs(List<Job> allAvailableJobs, Worker worker) {
		for (String filter : filters) {
			JobFilter jobFilter = (JobFilter) context.getBean(filter);
			allAvailableJobs = jobFilter.filter(allAvailableJobs, worker);
		}
	}

	private List<Job> getTopMatchingJobs(List<Job> allAvailableJobs) {
		// O(nlogn)
		// Collections.sort(allAvailableJobs,new JobComparator());

		// Heap to get top n matching jobs
		// O(n)
		PriorityQueue<Job> queue = new PriorityQueue<>(new JobComparator());
		for (Job job : allAvailableJobs) {
			queue.add(job);
			if (queue.size() > jobMatchCount)
				queue.poll();
		}
		List<Job> matchingJobs = new ArrayList<Job>(Arrays.asList(new Job[jobMatchCount]));
		int count = jobMatchCount;
		while (!queue.isEmpty()) {
			matchingJobs.set(--count, queue.poll());
		}
		return matchingJobs;
	}

}
