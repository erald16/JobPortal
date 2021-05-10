package com.ikubinfo.primefaces.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ikubinfo.primefaces.model.Job;
import com.ikubinfo.primefaces.model.JobContacts;
import com.ikubinfo.primefaces.model.request.JobListRequest;
import com.ikubinfo.primefaces.model.response.JobListResponse;
import com.ikubinfo.primefaces.repository.JobRepository;
import com.ikubinfo.primefaces.service.JobService;

@Service("jobService")
class JobServiceImpl implements JobService {

	private JobRepository jobRepository;

	public JobServiceImpl(JobRepository jobRepository) {
		super();
		this.jobRepository = jobRepository;
	}

	@Override
	public List<Job> getAll(JobListRequest title) {
		return jobRepository.getAll(title);
	}

	@Override
	public boolean save(Job job) {
		return jobRepository.save(job);
	}

	@Override
	public boolean create(Job job) {
		return jobRepository.create(job);
	}

	@Override
	public void delete(Job job) {
		jobRepository.delete(job);
	}

	public List<Job> filterJob(String category) {
		return jobRepository.filterJob(category);
	}

	@Override
	public List<JobContacts> jobContacts(int user_id) {
		return jobRepository.jobContact(user_id);
	}

	@Override
	public JobListResponse getJobs(JobListRequest request) {
		int totalCount = jobRepository.getJobCount(request);
		List<Job> jobs = jobRepository.getAll(request);
		
		return new JobListResponse(totalCount, jobs);
	}

}
