package com.ikubinfo.primefaces.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ikubinfo.primefaces.model.Resume;
import com.ikubinfo.primefaces.repository.ResumeRepository;
import com.ikubinfo.primefaces.service.ResumeService;

@Service("resumeService")
class ResumeServiceImpl implements ResumeService{
	
	private ResumeRepository resumeRepository;

	public ResumeServiceImpl(ResumeRepository resumeRepository) {
		super();
		this.resumeRepository = resumeRepository;
	}
	
	@Override
	public List<Resume> getAll(String path){
		return resumeRepository.getAll(path);
	}
	
	@Override
	public boolean save(Resume resume) {
		return resumeRepository.save(resume);
	}
	
	@Override
	public boolean create(Resume resume) {
		return resumeRepository.create(resume);
	}
	
	@Override
	public int insert(Resume resume) {
		return resumeRepository.insert(resume);
	}
	
	@Override
	public void delete(Resume resume) {
		resumeRepository.delete(resume);
	}
}
