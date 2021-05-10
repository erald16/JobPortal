package com.ikubinfo.primefaces.model.request;

import java.sql.Date;

public class JobListRequest {

	private String title;
	private String description;
	private boolean active;
	private Date createDate;
	private Date lastUpdate;
	private int first;
	private int pageSize;

	public JobListRequest(String title, String description, boolean active, Date created, Date updated, int first,
			int pageSize) {
		super();
		this.title = title;
		this.description = description;
		this.active = active;
		this.createDate = created;
		this.lastUpdate = updated;
		this.first = first;
		this.pageSize = pageSize;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	@Override
	public String toString() {
		return "JobListRequest [title=" + title + ", description=" + description + ", active=" + active
				+ ", createDate=" + createDate + ", lastUpdate=" + lastUpdate + ", first=" + first + ", pageSize="
				+ pageSize + "]";
	}
	
	
}