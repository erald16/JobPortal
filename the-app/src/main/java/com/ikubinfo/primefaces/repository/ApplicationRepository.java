package com.ikubinfo.primefaces.repository;

import java.util.List;

import com.ikubinfo.primefaces.model.Application;

public interface ApplicationRepository {
	
	List<Application> getAll(int userID);

	boolean save(Application application);

	boolean create(Application application);

	void delete(Application application);

}
