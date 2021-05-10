package com.ikubinfo.primefaces.model.request;

import java.sql.Date;

public class UserListRequest {

	private String username;
	private String fullname;
	private String accessLvl;
	private String gender;
	private String address;
	private String email;
	private String website;
	private int phoneNumber;
	private boolean active;
	private Date createDate;
	private Date lastUpdate;
	private int first;
	private int pageSize;

	public UserListRequest(String username, String fullname, String accesslevel, String gender, String address,
			String email, String website, int phonenr, boolean active, Date created, Date updated, int first,
			int pageSize) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.accessLvl = accesslevel;
		this.gender = gender;
		this.address = address;
		this.email = email;
		this.website = website;
		this.phoneNumber = phonenr;
		this.active = active;
		this.createDate = created;
		this.lastUpdate = updated;
		this.first = first;
		this.pageSize = pageSize;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAccessLvl() {
		return accessLvl;
	}

	public void setAccessLvl(String accessLvl) {
		this.accessLvl = accessLvl;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
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
		return "UserListRequest [username=" + username + ", fullname=" + fullname + ", accessLvl=" + accessLvl
				+ ", gender=" + gender + ", address=" + address + ", email=" + email + ", website=" + website
				+ ", phoneNumber=" + phoneNumber + ", active=" + active + ", createDate=" + createDate + ", lastUpdate="
				+ lastUpdate + ", first=" + first + ", pageSize=" + pageSize + "]";
	}

}
