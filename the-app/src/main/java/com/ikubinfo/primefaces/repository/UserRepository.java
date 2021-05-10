package com.ikubinfo.primefaces.repository;

import java.util.List;

import com.ikubinfo.primefaces.model.User;
import com.ikubinfo.primefaces.model.request.UserListRequest;

public interface UserRepository {
	
	List<User> getAll(UserListRequest name);

	boolean save(User user);

	boolean create(User user);

	void delete(User user);
	
	public int getUserCount(UserListRequest request);
}
