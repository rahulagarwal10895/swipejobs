package com.swipejobs.matchjob.data;

import java.util.List;

import com.swipejobs.matchjob.model.Worker;


public class WorkerData {
	
	private static List<Worker>  workers;
	
	
	public static void setWorkers(List<Worker> workers) {
		WorkerData.workers = workers;
	}

	public static List<Worker> getWorkers() {
		return WorkerData.workers;
	}
	

}
