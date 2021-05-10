package com.ikubinfo.primefaces.model.response;

import java.util.List;

import com.ikubinfo.primefaces.model.Job;

public class JobListResponse {

	private int totalRow;
	private List<Job> jobs;

	public JobListResponse(int totalRow, List<Job> jobs) {
		super();
		this.totalRow = totalRow;
		this.jobs = jobs;
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

}