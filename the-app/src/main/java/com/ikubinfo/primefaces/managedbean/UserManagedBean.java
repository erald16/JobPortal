package com.ikubinfo.primefaces.managedbean;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ikubinfo.primefaces.managedbean.data.UserDataModel;
import com.ikubinfo.primefaces.model.User;
import com.ikubinfo.primefaces.service.UserService;
import com.ikubinfo.primefaces.service.exceptions.UsernameIsTakenException;
import com.ikubinfo.primefaces.util.Messages;

@ManagedBean
@ViewScoped
public class UserManagedBean implements Serializable {

	private static final long serialVersionUID = -8496994316807506148L;

	Logger logger = LoggerFactory.getLogger(UserManagedBean.class);

	private User user;
	private List<User> users;

	private String username;
	private String password;
	private String address;
	private String name;
	private String aclevel;
	private String email;
	private int phoneNr;
	private String website;
	private String gender;
	private Date createdDate;
	private Date lastUpdate;

	@ManagedProperty(value = "#{userService}")
	private UserService userService;
	
	@ManagedProperty(value = "#{userDataModel}")
	private UserDataModel userDataModel;

	@ManagedProperty(value = "#{messages}")
	private Messages messages;

	@PostConstruct
	public void init() {
		users = userDataModel.load(0, 10, null, null);
		logger.info("Users got to UserManagedBean {}", users.size());
		user = new User();
	}

	public void getAll() {
		users = userService.getAll(null);
	}

	public void create() {
		User toAdd = new User();
		toAdd.setUsername(username);
		toAdd.setPassword(password);
		toAdd.setFullname(name);
		toAdd.setAccessLvl(aclevel);
		toAdd.setAddress(address);
		toAdd.setEmail(email);
		toAdd.setWebsite(website);
		toAdd.setGender(gender);
		toAdd.setPhoneNumber(phoneNr);
		toAdd.setActive(true);

		try {
			if (userService.create(toAdd)) {
				messages.showInfoMessage("User was added successfully");
				getAll();
			}
		} catch (UsernameIsTakenException e) {
			messages.showWarningMessage(e.getMessage());
		}

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Messages getMessages() {
		return messages;
	}

	public void setMessages(Messages messages) {
		this.messages = messages;
	}

	public void save() {
		if (userService.save(user)) {
			messages.showInfoMessage("User updated successfully");
		}
		user = new User();
	}

	public void delete() {
		userService.delete(user);
		messages.showInfoMessage("User Deleted");
	}

	public UserDataModel getUserDataModel() {
		return userDataModel;
	}

	public void setUserDataModel(UserDataModel userDataModel) {
		this.userDataModel = userDataModel;
	}
	
	
}
