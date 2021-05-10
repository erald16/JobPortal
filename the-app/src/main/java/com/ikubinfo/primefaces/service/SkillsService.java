package com.ikubinfo.primefaces.service;

import java.util.List;

import com.ikubinfo.primefaces.model.Skills;

public interface SkillsService {

	List<Skills> getAll(int userID);

	boolean save(Skills skills);

	boolean create(Skills skills);

	void delete(Skills skills);
	
	List<Skills> getUserSkills(int userID);	
}
  