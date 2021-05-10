package com.ikubinfo.primefaces.service;

import java.util.List;

import com.ikubinfo.primefaces.model.User;
import com.ikubinfo.primefaces.model.request.UserListRequest;
import com.ikubinfo.primefaces.model.response.UserListResponse;
import com.ikubinfo.primefaces.service.exceptions.UsernameIsTakenException;

public interface UserService {
	
	List<User> getAll(UserListRequest name);

	boolean save(User category);

	boolean create(User category) throws UsernameIsTakenException;

	void delete(User category);
	
	UserListResponse getUsers (UserListRequest request);
}
