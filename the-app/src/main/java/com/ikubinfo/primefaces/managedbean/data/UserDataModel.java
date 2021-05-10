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

import com.ikubinfo.primefaces.model.User;
import com.ikubinfo.primefaces.model.request.UserListRequest;
import com.ikubinfo.primefaces.model.response.UserListResponse;
import com.ikubinfo.primefaces.service.UserService;

@ManagedBean
@ViewScoped
public class UserDataModel extends LazyDataModel<User> {

	private static final long serialVersionUID = -9183754654280313658L;
	
	Logger logger = LoggerFactory.getLogger(UserDataModel.class);

	private String username;
	private String address;
	private String name;
	private String aclevel;
	private String email;
	private int phoneNr;
	private String website;
	private String gender;
	private boolean active;
	private Date createdDate;
	private Date lastUpdate;
	private int first;
	private int pageSize;

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	@Override
	public List<User> load(int first, int pageSize, Map<String, SortMeta> sortMeta, Map<String, FilterMeta> filterMeta) {
		this.first = first;
		this.pageSize = pageSize;
		
		UserListRequest request = new UserListRequest(username, address, name, aclevel, email, gender, website, phoneNr, active, createdDate, lastUpdate, first, pageSize);

		UserListResponse response = userService.getUsers(request);

		this.setRowCount(response.getTotalRow());
		logger.info("user list response {}", response.getUsers().size());
		return response.getUsers();
	}

	public void filter() {

		UserListRequest request = new UserListRequest(username, address, name, aclevel, email, gender, website, phoneNr, active, createdDate, lastUpdate, first, pageSize);

		UserListResponse response = userService.getUsers(request);

		this.setRowCount(response.getTotalRow());
		this.setWrappedData(response.getUsers());

	}

	public void refresh() {
		this.name = null;

		UserListResponse response = userService.getUsers(new UserListRequest(username, address, name, aclevel, email, gender, website, phoneNr, active, createdDate, lastUpdate, first, pageSize));

		this.setRowCount(response.getTotalRow());
		this.setWrappedData(response.getUsers());

	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAclevel() {
		return aclevel;
	}


	public void setAclevel(String aclevel) {
		this.aclevel = aclevel;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getPhoneNr() {
		return phoneNr;
	}


	public void setPhoneNr(int phoneNr) {
		this.phoneNr = phoneNr;
	}


	public String getWebsite() {
		return website;
	}


	public void setWebsite(String website) {
		this.website = website;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
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


	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
