package com.ikubinfo.primefaces.model.request;

import java.sql.Date;

public class CategoryListRequest {

	private String name;
	private String description;
	private boolean active;
	private Date createDate;
	private Date lastUpdate;
	private int first;
	private int pageSize;

	public CategoryListRequest(String name, String description, boolean active, Date created, Date updated, int first,
			int pageSize) {
		super();
		this.name = name;
		this.description = description;
		this.active = active;
		this.createDate = created;
		this.lastUpdate = updated;
		this.first = first;
		this.pageSize = pageSize;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}