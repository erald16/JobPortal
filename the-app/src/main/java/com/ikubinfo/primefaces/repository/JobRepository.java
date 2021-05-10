package com.ikubinfo.primefaces.repository;

import java.util.List;

import com.ikubinfo.primefaces.model.Job;
import com.ikubinfo.primefaces.model.JobContacts;
import com.ikubinfo.primefaces.model.request.JobListRequest;

public interface JobRepository {

	List<Job> getAll(JobListRequest name);

	boolean save(Job job);

	boolean create(Job job);

	void delete(Job job);

	List<Job> filterJob(String category);
	
	List<JobContacts> jobContact (int user_ID);
	
	public int getJobCount(JobListRequest request);

}