package com.ikubinfo.primefaces.model;

import java.sql.Date;

public class Resume {
	
	private int resumeID;
	private String resumePath;
	private Date createDate;
   	private Date lastUpdate;
	
   	public Resume() {
		super();
	}

	public Resume(int resumeID, String resumePath, Date createDate, Date lastUpdate) {
		super();
		this.resumeID = resumeID;
		this.resumePath = resumePath;
		this.createDate = createDate;
		this.lastUpdate = lastUpdate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
		result = prime * result + resumeID;
		result = prime * result + ((resumePath == null) ? 0 : resumePath.hashCode());
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
		Resume other = (Resume) obj;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (lastUpdate == null) {
			if (other.lastUpdate != null)
				return false;
		} else if (!lastUpdate.equals(other.lastUpdate))
			return false;
		if (resumeID != other.resumeID)
			return false;
		if (resumePath == null) {
			if (other.resumePath != null)
				return false;
		} else if (!resumePath.equals(other.resumePath))
			return false;
		return true;
	}

	public int getResumeID() {
		return resumeID;
	}

	public void setResumeID(int resumeID) {
		this.resumeID = resumeID;
	}

	public String getResumePath() {
		return resumePath;
	}

	public void setResumePath(String resumePath) {
		this.resumePath = resumePath;
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

	@Override
	public String toString() {
		return "Resume [resumeID=" + resumeID + ", resumePath=" + resumePath + ", createDate=" + createDate
				+ ", lastUpdate=" + lastUpdate + "]";
	}
   	
   	

}
