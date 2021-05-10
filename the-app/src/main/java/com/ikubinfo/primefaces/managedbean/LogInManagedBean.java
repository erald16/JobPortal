package com.ikubinfo.primefaces.managedbean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.ikubinfo.primefaces.model.User;
import com.ikubinfo.primefaces.service.exceptions.UsernameIsTakenException;
import com.ikubinfo.primefaces.service.impl.UserServiceImpl;
import com.ikubinfo.primefaces.util.Messages;
import com.ikubinfo.primefaces.util.SessionUtils;

@ManagedBean
@SessionScoped
public class LogInManagedBean implements Serializable{
	private static final long serialVersionUID = 3881343616235406929L;
	private String username;
	private String password;

	private String newUsername;
	private String newPassword;
	private String newFullname;
	private String newAccessLevel;
	private String newGender;
	private String newAddress;
	private int newPhoneNumber;
	private boolean newActive = true;
	
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

	public UserServiceImpl getUserService() {
		return userService;
	}

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	@ManagedProperty(value = "#{userService}")
	private UserServiceImpl userService;
	
	@ManagedProperty(value = "#{messages}")
	private Messages messages;
	
	public String getNewUsername() {
		return newUsername;
	}

	public void setNewUsername(String newUsername) {
		this.newUsername = newUsername;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewFullname() {
		return newFullname;
	}

	public void setNewFullname(String newFullname) {
		this.newFullname = newFullname;
	} 

	public String getNewAccessLevel() {
		return newAccessLevel;
	}

	public void setNewAccessLevel(String newAccessLevel) {
		this.newAccessLevel = newAccessLevel;
	}

	public String getNewGender() {
		return newGender;
	}

	public void setNewGender(String newGender) {
		this.newGender = newGender;
	}

	public String getNewAddress() {
		return newAddress;
	}

	public void setNewAddress(String newAddress) {
		this.newAddress = newAddress;
	}

	public int getNewPhoneNumber() {
		return newPhoneNumber;
	}

	public void setNewPhoneNumber(int newPhoneNumber) {
		this.newPhoneNumber = newPhoneNumber;
	}

	public boolean isNewActive() {
		return newActive;
	}

	public void setNewActive(boolean newActive) {
		this.newActive = newActive;
	}

	public Messages getMessages() {
		return messages;
	}

	public void setMessages(Messages messages) {
		this.messages = messages;
	}
	
	@PostConstruct
	public void init() {
		System.out.println(SessionUtils.getSession().getAttribute("destination"));
	}

	public void validateCredentials() {
		User user = userService.validate(username, password);
		if (user != null) {
			
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", username);
			session.setAttribute("user", user);
			String destination = (String)session.getAttribute("destination");
			if (destination == null)
				System.out.println(session.getAttribute("username"));
			else
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect(destination);
				} catch (IOException e) {
					e.printStackTrace();
				}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong credentials", "Wrong username or password"));
		}
	}
	public void registerJobSeeker() {
		User user = new User();
		user.setUsername(newUsername);
		user.setPassword(newPassword);
		user.setFullname(newFullname);
		user.setAddress(newAddress);
		user.setGender(newGender);
		user.setAccessLvl("js");
		user.setActive(true);
		
		try {
			userService.create(user);
		} catch (UsernameIsTakenException e) {
			messages.showWarningMessage(e.getMessage());
		}
	}
	public void registerJobOfferer() {
		User user = new User();
		user.setUsername(newUsername);
		user.setPassword(newPassword);
		user.setFullname(newFullname);
		user.setAddress(newAddress);
		user.setGender(newGender);
		user.setAccessLvl("jo");
		user.setActive(true);
		
		try {
			userService.create(user);
		} catch (UsernameIsTakenException e) {
			messages.showWarningMessage(e.getMessage());
		}
	}
	
	public void logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
	}
}
