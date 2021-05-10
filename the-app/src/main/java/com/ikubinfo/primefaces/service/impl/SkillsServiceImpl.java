package com.ikubinfo.primefaces.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ikubinfo.primefaces.model.Skills;
import com.ikubinfo.primefaces.repository.SkillsRepository;
import com.ikubinfo.primefaces.service.SkillsService;

@Service("skillsService")
class SkillsServiceImpl implements SkillsService{
	
	private SkillsRepository skillsRepository;
	
	public SkillsServiceImpl(SkillsRepository skillsRepository) {
		this.skillsRepository = skillsRepository;
	}
	
	@Override
	public List<Skills> getAll(int userID) {
		return skillsRepository.getAll(userID);
	}
	
	@Override
	public boolean save(Skills skills) {
		return skillsRepository.save(skills);
		
	}  
	
	@Override
	public boolean create(Skills skills) {
		return skillsRepository.create(skills);
	}
	
	@Override
	public void delete(Skills skills) {
		skillsRepository.delete(skills);
	}

	@Override
	public List<Skills> getUserSkills(int userID) {
		return skillsRepository.getUserSkills(userID);
	}
}