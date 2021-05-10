package com.ikubinfo.primefaces.service;

import java.util.List;

import com.ikubinfo.primefaces.model.Application;

public interface ApplicationService {

	List<Application> getAll(int userID) ;

	boolean save(Application application);

	boolean create(Application application);

	void delete(Application application);
}
