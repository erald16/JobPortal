package com.ikubinfo.primefaces.managedbean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

import com.ikubinfo.primefaces.managedbean.data.JobDataModel;
import com.ikubinfo.primefaces.model.Categories;
import com.ikubinfo.primefaces.model.Job;
import com.ikubinfo.primefaces.model.JobContacts;
import com.ikubinfo.primefaces.model.Skills;
import com.ikubinfo.primefaces.model.User;
import com.ikubinfo.primefaces.service.CategoryService;
import com.ikubinfo.primefaces.service.JobService;
import com.ikubinfo.primefaces.service.SkillsService;
import com.ikubinfo.primefaces.util.Messages;
import com.ikubinfo.primefaces.util.SessionUtils;

@ManagedBean
@ViewScoped
public class JobManagedBean implements Serializable {
	private static final long serialVersionUID = 3800933422824282320L;

	private List<Job> jobs;
	private List<JobContacts> jobc;
	private List<Categories> categories;
	private List<Skills> skills;

	private String categoryName;
	private Job jobToAdd;

	@ManagedProperty(value = "#{jobService}")
	private JobService jobService;

	@ManagedProperty(value = "#{jobDataModel}")
	private JobDataModel jobDataModel;

	@ManagedProperty(value = "#{categoryService}")
	private CategoryService categoryService;

	@ManagedProperty(value = "#{skillsService}")
	private SkillsService skillsService;

	@ManagedProperty(value = "#{messages}")
	private Messages messages;

	@PostConstruct
	public void init() {
		jobs = jobService.filterJob(null);
		categories = categoryService.getAll(null);
		skills = skillsService.getAll(-1);
		jobs = jobDataModel.load(0, 10, null, null);
		jobToAdd = new Job();
	}

	public void getAll() {
		jobs = jobService.getAll(null);
	}

	public void filter() {
		jobs = jobService.filterJob(categoryName);
	}

	public void reset() {
		categoryName = null;
		filter();
	}

	public void insert() {
		boolean errors = validateInput();
		if (errors) {
			return;
		}
	
		HttpSession session = SessionUtils.getSession();
		User user = (User) session.getAttribute("user");
		jobToAdd.setEmployeeID(user.getUserID());

		if (jobService.create(jobToAdd)) {
			messages.showInfoMessage("Job was added successfully");
			
		}

	}

	private boolean validateInput() {
		boolean errors = false;
		if (Objects.isNull(jobToAdd.getTitle()) || jobToAdd.getTitle().isEmpty()) {
			errors = true;
			messages.showErrorMessage("Job title should not be empty!");
			return errors;
		}
		if (jobToAdd.getTitle().length() < 3) {
			errors = true;
			messages.showErrorMessage("Job title should have at least 3 characters!");
			return errors;
		}
		if (Objects.isNull(jobToAdd.getDescription()) || jobToAdd.getDescription().isEmpty()) {
			errors = true;
			messages.showErrorMessage("Job description should not be empty!");
			return errors;
		}
		return errors;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobToAdd(Job job) {
		this.jobToAdd = job;
	}

	public Job getJobToAdd() {
		return jobToAdd;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String name) {
		this.categoryName = name;
	}

	public JobService getJobService() {
		return jobService;
	}

	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}

	public Messages getMessages() {
		return messages;
	}

	public void setMessages(Messages messages) {
		this.messages = messages;
	}

	public List<Categories> getCategories() {
		return categories;
	}

	public void setCategories(List<Categories> categories) {
		this.categories = categories;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
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

	public JobDataModel getJobDataModel() {
		return jobDataModel;
	}

	public void setJobDataModel(JobDataModel jobDataModel) {
		this.jobDataModel = jobDataModel;
	}

}
