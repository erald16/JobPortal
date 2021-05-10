package com.ikubinfo.primefaces.service;

import java.util.List;

import com.ikubinfo.primefaces.model.Job;
import com.ikubinfo.primefaces.model.JobContacts;
import com.ikubinfo.primefaces.model.request.JobListRequest;
import com.ikubinfo.primefaces.model.response.JobListResponse;

public interface JobService {

	List<Job> getAll(JobListRequest title);

	boolean save(Job job);

	boolean create(Job job);

	void delete(Job job);
	
	List<Job> filterJob(String category);
	
	List<JobContacts> jobContacts(int user_id);
	
	JobListResponse getJobs (JobListRequest request);
}