package com.ikubinfo.primefaces.service;

import java.util.List;

import com.ikubinfo.primefaces.model.Experience;

public interface ExperienceService {

	List<Experience> getAll(int userID);

	boolean save(Experience experience);

	boolean create(Experience experience);

	void delete(Experience experience);
}
