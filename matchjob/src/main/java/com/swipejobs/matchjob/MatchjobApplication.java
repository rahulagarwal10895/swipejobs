package com.swipejobs.matchjob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.swipejobs.matchjob.service.WorkerService;

@SpringBootApplication
public class MatchjobApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MatchjobApplication.class, args);
	}

}
