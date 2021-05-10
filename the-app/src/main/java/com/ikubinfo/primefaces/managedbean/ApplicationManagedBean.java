package com.ikubinfo.primefaces.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.ikubinfo.primefaces.model.Application;
import com.ikubinfo.primefaces.service.ApplicationService;
import com.ikubinfo.primefaces.util.Messages;

@ManagedBean
@ViewScoped
public class ApplicationManagedBean implements Serializable{
	private static final long serialVersionUID = -2939727154524739944L;

	private Application application;
	
	private String status;
	private int userID;
	private int jobID;

	private List<Application> applications;

	@ManagedProperty(value = "#{applicationService}")
	private ApplicationService applicationService;

	@ManagedProperty(value = "#{messages}")
	private Messages messages;

	@PostConstruct
	public void init() {
		applications = applicationService.getAll(-1);
		application = new Application();
	}
	
	public void getAll() {
		applications = applicationService.getAll(-1);
	}
	
	public void create() {
		Application toAdd = new Application();

		if (applicationService.create(toAdd)) {
			messages.showInfoMessage("Skill was added successfully");
			getAll();
		}  
	}
	
	public void save() {
		if (applicationService.save(application)) {
			getAll();
			messages.showInfoMessage("Skill updated successfully");

		}
		application = new Application();
	}
	
	public void delete() {
		applicationService.delete(application);
		applications = applicationService.getAll(-1);
		messages.showInfoMessage("Deleted");
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public ApplicationService getApplicationService() {
		return applicationService;
	}

	public void setApplicationService(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	public Messages getMessages() {
		return messages;
	}

	public void setMessages(Messages messages) {
		this.messages = messages;
	}
	
}
