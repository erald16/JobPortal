package com.ikubinfo.primefaces.managedbean;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;

import com.ikubinfo.primefaces.model.Application;
import com.ikubinfo.primefaces.model.Experience;
import com.ikubinfo.primefaces.model.Resume;
import com.ikubinfo.primefaces.model.Skills;
import com.ikubinfo.primefaces.model.User;
import com.ikubinfo.primefaces.service.ApplicationService;
import com.ikubinfo.primefaces.service.ExperienceService;
import com.ikubinfo.primefaces.service.ResumeService;
import com.ikubinfo.primefaces.service.SkillsService;
import com.ikubinfo.primefaces.service.UserService;
import com.ikubinfo.primefaces.util.Messages;
import com.ikubinfo.primefaces.util.SessionUtils;

@ManagedBean
@ViewScoped
public class ProfileManagedBean implements Serializable {
	private static final long serialVersionUID = -4598324027296276284L;

	private User user;

	private Skills skill;

	// experience variables
	private Experience experience;

	private boolean showExperience;

	private String position;
	private String description;

	private java.util.Date insertStartDate;
	private java.util.Date insertEndDate;

	private java.util.Date updateStartDate;
	private java.util.Date updateEndDate;

	private Date startDate;
	private Date endDate;

	private List<Skills> skills;

	private List<Experience> experiences;

	private UploadedFile uploadedFile;

	private List<Application> applications;

	@ManagedProperty(value = "#{skillsService}")
	private SkillsService skillsService;

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	@ManagedProperty(value = "#{experienceService}")
	private ExperienceService experienceService;

	@ManagedProperty(value = "#{applicationService}")
	private ApplicationService applicationService;

	@ManagedProperty(value = "#{messages}")
	private Messages messages;

	@ManagedProperty(value = "#{resumeService}")
	private ResumeService resumeService;

	@PostConstruct
	public void init() {
		HttpSession session = SessionUtils.getSession();
		user = (User) session.getAttribute("user");
		skills = skillsService.getAll(user.getUserID());
		skill = new Skills();
		experiences = experienceService.getAll(user.getUserID());
		experience = new Experience();
		applications = applicationService.getAll(user.getUserID());
		showExperience = false;
	}

	public void showExperienceInsert() {
		showExperience = true;
	}

	public void saveUser() {
		userService.save(user);
		HttpSession session = SessionUtils.getSession();
		session.setAttribute("user", user);
	}

	public void saveExperience() {
		System.out.println("save called");
		System.out.println(updateStartDate);
		experience.setStartDate(new java.sql.Date(updateStartDate.getTime()));
		experience.setEndDate(new java.sql.Date(updateEndDate.getTime()));
		if (experienceService.save(experience)) {
			showExperience = false;
			experiences = experienceService.getAll(user.getUserID());
			messages.showInfoMessage("Skill updated successfully");

		}
		experience = new Experience();

	}

	public void createExperience() {

		Experience toAdd = new Experience();
		toAdd.setPosition(position);
		toAdd.setDescription(description);
		toAdd.setStartDate(new Date(insertStartDate.getTime()));
		toAdd.setEndDate(new Date(insertEndDate.getTime()));

		if (experienceService.create(toAdd)) {
			showExperience = false;
			experiences = experienceService.getAll(user.getUserID());
			messages.showInfoMessage("Skill was added successfully");
		}
	}

	public boolean isShowExperience() {
		return showExperience;
	}

	public void setShowExperience(boolean showExperience) {
		this.showExperience = showExperience;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Skills getSkill() {
		return skill;
	}

	public void setSkill(Skills skill) {
		this.skill = skill;
	}

	public Experience getExperience() {
		return experience;
	}

	public void setExperience(Experience experience) {
		this.experience = experience;
	}

	public List<Skills> getSkills() {
		return skills;
	}

	public void setSkills(List<Skills> skills) {
		this.skills = skills;
	}

	public List<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public SkillsService getSkillsService() {
		return skillsService;
	}

	public void setSkillsService(SkillsService skillsService) {
		this.skillsService = skillsService;
	}

	public ExperienceService getExperienceService() {
		return experienceService;
	}

	public void setExperienceService(ExperienceService experienceService) {
		this.experienceService = experienceService;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// Resume related code

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile file) {
		this.uploadedFile = file;
	}

	public boolean uploadResume() {
		Resume toAdd = new Resume();

		Path folder = Paths.get("D://uploads");
		String filename = FilenameUtils.getBaseName(uploadedFile.getFileName());
		String extension = FilenameUtils.getExtension(uploadedFile.getFileName());
		Path file;
		try {
			file = Files.createTempFile(folder, filename + "-", "." + extension);
			InputStream input = uploadedFile.getInputStream();
			Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("Uploaded file successfully saved in " + file);

		} catch (IOException e) {
			e.printStackTrace();
		}

		toAdd.setResumePath(filename + extension);

		int resumeId = resumeService.insert(toAdd);
		user.setResumeID(resumeId);
		userService.save(user);
		messages.showInfoMessage("Resume was added successfully");

		return false;
	}

	public ResumeService getResumeService() {
		return resumeService;
	}

	public void setResumeService(ResumeService resumeService) {
		this.resumeService = resumeService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
