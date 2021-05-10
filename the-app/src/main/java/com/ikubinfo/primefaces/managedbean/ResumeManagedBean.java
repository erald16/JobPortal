package com.ikubinfo.primefaces.managedbean;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;

import com.ikubinfo.primefaces.model.Resume;
import com.ikubinfo.primefaces.service.ResumeService;
import com.ikubinfo.primefaces.util.Messages;

@ManagedBean
@ViewScoped
public class ResumeManagedBean {
	private Resume resume;
	private String path;
	private UploadedFile uploadedFile;
	
	private List<Resume> resumes;

	@ManagedProperty(value = "#{resumeService}")
	private ResumeService resumeService;

	@ManagedProperty(value = "#{messages}")
	private Messages messages;

	@PostConstruct
	public void init() {
		//resumes = resumeService.getAll(path);
		resume = new Resume();
	}

	public void getAll() {
		resumes = resumeService.getAll(null);
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile file) {
		this.uploadedFile = file;
	}
	
	public boolean create() {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		toAdd.setResumePath(filename + extension);
		
		if (resumeService.create(toAdd)) {
			messages.showInfoMessage("Resume was added successfully");
		}
		return false;
	}

	public void save() {
		if (resumeService.save(resume)) {
			messages.showInfoMessage("Resume updated successfully");

		}
		resume = new Resume();
	}

	public void delete() {
		resumeService.delete(resume);
		messages.showInfoMessage("Resume Deleted");
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<Resume> getResumes() {
		return resumes;
	}

	public void setResumes(List<Resume> resumes) {
		this.resumes = resumes;
	}

	public ResumeService getResumeService() {
		return resumeService;
	}

	public void setResumeService(ResumeService resumeService) {
		this.resumeService = resumeService;
	}

	public Messages getMessages() {
		return messages;
	}

	public void setMessages(Messages messages) {
		this.messages = messages;
	}
}
