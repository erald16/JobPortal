package com.ikubinfo.primefaces.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ikubinfo.primefaces.model.Experience;
import com.ikubinfo.primefaces.repository.impl.ExperienceRepositoryImpl;
import com.ikubinfo.primefaces.service.ExperienceService;

@Service("experienceService")
public class ExperienceServiceImpl implements ExperienceService {

	private ExperienceRepositoryImpl experienceRepository;

	public ExperienceServiceImpl(ExperienceRepositoryImpl experienceRepository) {
		this.experienceRepository = experienceRepository;
	}

	@Override
	public List<Experience> getAll(int userID) {
		return experienceRepository.getAll(userID);
	}

	@Override
	public boolean save(Experience experience) {
		return experienceRepository.save(experience);

	}

	@Override
	public boolean create(Experience experience) {
		return experienceRepository.create(experience);

	}

	@Override
	public void delete(Experience experience) {
		experienceRepository.delete(experience);
	}
}
