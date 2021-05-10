package com.ikubinfo.primefaces.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ikubinfo.primefaces.model.Application;
import com.ikubinfo.primefaces.repository.ApplicationRepository;
import com.ikubinfo.primefaces.service.ApplicationService;

@Service("applicationService")
class ApplicationServiceImpl implements ApplicationService{
	
	private ApplicationRepository applicationRepository;

	public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
		super();
		this.applicationRepository = applicationRepository;
	}
	
	@Override
	public List<Application> getAll(int userID){
		return applicationRepository.getAll(userID);
	}
	
	@Override
	public boolean save(Application application) {
		return applicationRepository.save(application);
	}
	
	@Override
	public boolean create(Application application) {
		return applicationRepository.create(application);
	}
	
	public void delete(Application application) {
		applicationRepository.delete(application);
	}
}
