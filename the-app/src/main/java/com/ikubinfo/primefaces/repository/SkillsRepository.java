package com.ikubinfo.primefaces.repository;

import java.util.List;

import com.ikubinfo.primefaces.model.Skills;

public interface SkillsRepository {
	List<Skills> getAll(int userID);

	boolean save(Skills skills);

	boolean create(Skills skills);
	
	void delete(Skills skills);
	
	List<Skills> getUserSkills(int userID);

}
  