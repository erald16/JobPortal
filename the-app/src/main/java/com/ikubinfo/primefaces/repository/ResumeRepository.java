package com.ikubinfo.primefaces.repository;

import java.util.List;

import com.ikubinfo.primefaces.model.Resume;

public interface ResumeRepository {
	List<Resume> getAll(String path);
	
	boolean save(Resume resume);
	
	boolean create(Resume resume);
	
	int insert(Resume resume);
	
	void delete(Resume resume);
}
