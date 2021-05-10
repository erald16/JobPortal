package com.ikubinfo.primefaces.managedbean;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.ikubinfo.primefaces.model.Experience;
import com.ikubinfo.primefaces.service.ExperienceService;
import com.ikubinfo.primefaces.util.Messages;


@ManagedBean
@ViewScoped
public class ExperienceManagedBean implements Serializable{
	private static final long serialVersionUID = 5319942872126268536L;

	private Experience experience;
	
	private String position;
	private String description;
	
	private java.util.Date insertStartDate;
	private java.util.Date insertEndDate;
	
	private java.util.Date updateStartDate;
	private java.util.Date updateEndDate;
	
	private Date startDate;
	private Date endDate;

	private List<Experience> experiences;

	@ManagedProperty(value = "#{experienceService}")
	private ExperienceService experienceService;

	@ManagedProperty(value = "#{messages}")
	private Messages messages;

	@PostConstruct
	public void init() {
		experiences = experienceService.getAll(-1);
		experience = new Experience();
	}
	
	public void getAll() {
		experiences = experienceService.getAll(-1);
	}
	
	public void create() {
		
		Experience toAdd = new Experience();
		toAdd.setPosition(position);
		toAdd.setDescription(description);
		toAdd.setStartDate(new Date(insertStartDate.getTime()));
		toAdd.setEndDate(new Date(insertEndDate.getTime()));

		if (experienceService.create(toAdd)) {
			messages.showInfoMessage("Skill was added successfully");
			getAll();
		}  
	}
	
	public void save() {
		System.out.println("save called");
		System.out.println(updateStartDate);
		experience.setStartDate(new java.sql.Date(updateStartDate.getTime()));
		experience.setEndDate(new java.sql.Date(updateEndDate.getTime()));
		if (experienceService.save(experience)) {
			getAll();
			messages.showInfoMessage("Skill updated successfully");

		}
		experience = new Experience();

	}
	
	public void delete() {
		experienceService.delete(experience);
		getAll();
		messages.showInfoMessage("Deleted");
		
	}

	public Experience getExperience() {
		return experience;
	}

	public void setExperience(Experience experience) {
		this.experience = experience;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

	public ExperienceService getExperienceService() {
		return experienceService;
	}

	public void setExperienceService(ExperienceService experienceService) {
		this.experienceService = experienceService;
	}

	public Messages getMessages() {
		return messages;
	}

	public void setMessages(Messages messages) {
		this.messages = messages;
	}

	public java.util.Date getInsertStartDate() {
		return insertStartDate;
	}

	public void setInsertStartDate(java.util.Date insertStartDate) {
		this.insertStartDate = insertStartDate;
	}

	public java.util.Date getInsertEndDate() {
		return insertEndDate;
	}

	public void setInsertEndDate(java.util.Date insertEndDate) {
		this.insertEndDate = insertEndDate;
	}

	public java.util.Date getUpdateStartDate() {
		return updateStartDate;
	}

	public void setUpdateStartDate(java.util.Date updateStartDate) {
		this.updateStartDate = updateStartDate;
	}

	public java.util.Date getUpdateEndDate() {
		return updateEndDate;
	}

	public void setUpdateEndDate(java.util.Date updateEndDate) {
		this.updateEndDate = updateEndDate;
	}
	
	
}
