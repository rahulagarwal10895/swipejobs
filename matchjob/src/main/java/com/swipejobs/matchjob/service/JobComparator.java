package com.swipejobs.matchjob.service;

import java.util.Comparator;

import com.swipejobs.matchjob.model.Job;

public class JobComparator implements Comparator<Job> {

	@Override
	public int compare(Job o1, Job o2) {

		//Sort on Relevance than
		//Sort on Certificates size than
		//Sort on Distance
		
		if (o1.getRelevance().equals(o2.getRelevance())) {
			if (o1.getRequiredCertificates().size() == o2.getRequiredCertificates().size()) {
				return (int) (o2.getDistance() - o1.getDistance());
			} else {
				return o1.getRequiredCertificates().size() - o2.getRequiredCertificates().size();
			}
		} else {
			return (int) ((o1.getRelevance() - o2.getRelevance()) * 100);
		}
	}
	

}
