package com.ikubinfo.primefaces.repository;

import java.util.List;

import com.ikubinfo.primefaces.model.Experience;

public interface ExperienceRepository {

	List<Experience> getAll(int userID);

	boolean save(Experience experience);

	boolean create(Experience experience);

	boolean delete(Experience experience);

}
