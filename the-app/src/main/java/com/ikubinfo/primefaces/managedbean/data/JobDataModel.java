package com.ikubinfo.primefaces.managedbean.data;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ikubinfo.primefaces.model.Job;
import com.ikubinfo.primefaces.model.request.JobListRequest;
import com.ikubinfo.primefaces.model.response.JobListResponse;
import com.ikubinfo.primefaces.service.JobService;

@ManagedBean
@ViewScoped
public class JobDataModel extends LazyDataModel<Job> {

	private static final long serialVersionUID = 7057385734460370549L;

	Logger logger = LoggerFactory.getLogger(JobDataModel.class);

	private String title;
	private String description;
	private boolean active;
	private Date createdDate;
	private Date lastUpdate;
	private int first;
	private int pageSize;

	@ManagedProperty(value = "#{jobService}")
	private JobService jobService;

	@Override
	public List<Job> load(int first, int pageSize, Map<String, SortMeta> sortMeta, Map<String, FilterMeta> filterMeta) {
		this.first = first;
		this.pageSize = pageSize;

		JobListRequest request = new JobListRequest(title, description, active, createdDate, lastUpdate, first,
				pageSize);

		JobListResponse response = jobService.getJobs(request);

		this.setRowCount(response.getTotalRow());
		logger.info("user list response {}", response.getJobs().size());
		return response.getJobs();
	}

	public void filter() {

		JobListRequest request = new JobListRequest(title, description, active, createdDate, lastUpdate, first,
				pageSize);

		JobListResponse response = jobService.getJobs(request);

		this.setRowCount(response.getTotalRow());
		this.setWrappedData(response.getJobs());

	}

	public void refresh() {
		this.title = null;

		JobListResponse response = jobService
				.getJobs(new JobListRequest(title, description, active, createdDate, lastUpdate, first, pageSize));

		this.setRowCount(response.getTotalRow());
		this.setWrappedData(response.getJobs());

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public JobService getJobService() {
		return jobService;
	}

	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}

}
