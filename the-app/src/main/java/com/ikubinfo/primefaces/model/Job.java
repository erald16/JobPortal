package com.ikubinfo.primefaces.model;

import java.util.Date;

public class Job {

	private int jobID;
	private String title;
	private String description;
	private boolean active;
	private Date createDate;
	private Date lastupDate;
	private int skillID;
	private int employeeID;
	private int categoryID;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + categoryID;
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + employeeID;
		result = prime * result + jobID;
		result = prime * result + ((lastupDate == null) ? 0 : lastupDate.hashCode());
		result = prime * result + skillID;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Job other = (Job) obj;
		if (active != other.active)
			return false;
		if (categoryID != other.categoryID)
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (employeeID != other.employeeID)
			return false;
		if (jobID != other.jobID)
			return false;
		if (lastupDate == null) {
			if (other.lastupDate != null)
				return false;
		} else if (!lastupDate.equals(other.lastupDate))
			return false;
		if (skillID != other.skillID)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
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

	public Date getLastupDate() {
		return lastupDate;
	}

	public void setLastupDate(Date lastupDate) {
		this.lastupDate = lastupDate;
	}

	public int getSkillID() {
		return skillID;
	}

	public void setSkillID(int skillID) {
		this.skillID = skillID;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}


	@Override
	public String toString() {
		return "Job [jobID=" + jobID + ", title=" + title + ", description=" + description + ", active=" + active
				+ ", createDate=" + createDate + ", lastupDate=" + lastupDate + ", skillID=" + skillID + ", employeeID="
				+ employeeID + ", categoryID=" + categoryID + "]";
	}

}