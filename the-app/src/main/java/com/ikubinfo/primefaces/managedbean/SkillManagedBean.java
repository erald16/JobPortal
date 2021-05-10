package com.ikubinfo.primefaces.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.ikubinfo.primefaces.model.Skills;
import com.ikubinfo.primefaces.service.SkillsService;
import com.ikubinfo.primefaces.util.Messages;

@ManagedBean
@ViewScoped
public class SkillManagedBean implements Serializable{
	private static final long serialVersionUID = -535778777527463386L;

	private Skills skill;
	
	private String type;
	private String description;

	private List<Skills> skills;

	@ManagedProperty(value = "#{skillsService}")
	private SkillsService skillsService;

	@ManagedProperty(value = "#{messages}")
	private Messages messages;

	@PostConstruct
	public void init() {
		skills = skillsService.getAll(-1);
		skill = new Skills();
	}
	
	public void getAll() {
		skills = skillsService.getAll(-1);
	}
	
	public void create() {
		Skills toAdd = new Skills();
		toAdd.setType(type);
		toAdd.setDescription(description);

		if (skillsService.create(toAdd)) {
			messages.showInfoMessage("Skill was added successfully");
			getAll();
		}  
	}
	
	public void save() {
		if (skillsService.save(skill)) {
			getAll();
			messages.showInfoMessage("Skill updated successfully");

		}
		skill = new Skills();

	}
	
	public void delete() {
		skillsService.delete(skill);
		skills = skillsService.getAll(-1);
		messages.showInfoMessage("Deleted");
	}
	
	public Skills getSkill() {
		return skill;
	}

	public void setSkill(Skills skill) {
		this.skill = skill;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Skills> getSkills() {
		return skills;
	}

	public void setSkills(List<Skills> skills) {
		this.skills = skills;
	}

	public SkillsService getSkillsService() {
		return skillsService;
	}

	public void setSkillsService(SkillsService skillsService) {
		this.skillsService = skillsService;
	}

	public Messages getMessages() {
		return messages;
	}

	public void setMessages(Messages messages) {
		this.messages = messages;
	}
}
