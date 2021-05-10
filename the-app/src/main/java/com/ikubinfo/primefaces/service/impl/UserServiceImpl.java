package com.ikubinfo.primefaces.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ikubinfo.primefaces.model.User;
import com.ikubinfo.primefaces.model.request.UserListRequest;
import com.ikubinfo.primefaces.model.response.UserListResponse;
import com.ikubinfo.primefaces.repository.impl.UserRepositoryImpl;
import com.ikubinfo.primefaces.service.UserService;
import com.ikubinfo.primefaces.service.exceptions.UsernameIsTakenException;

@Service("userService")
public class UserServiceImpl implements UserService {
	private UserRepositoryImpl userRepository;

	public UserServiceImpl(UserRepositoryImpl userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getAll(UserListRequest name) {
		return userRepository.getAll(name);
	}

	@Override
	public boolean save(User user) {
		return userRepository.save(user);

	}

	@Override
	public boolean create(User user) throws UsernameIsTakenException {
		if (userRepository.isUsernameTaken(user.getUsername()))
			throw new UsernameIsTakenException("This username is taken");
		else {
			return userRepository.create(user);
		}
	}
	
	public User validate(String username, String password) {
		// if the below code returns true it means the credentials were valid
		User user = userRepository.validate(username, password);
		if (user != null)
			return user;
		else
			return null;
	}
	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}
	
	@Override
	public UserListResponse getUsers(UserListRequest request) {
		int totalCount = userRepository.getUserCount(request);
		List<User> users = userRepository.getAll(request);

		return new UserListResponse(totalCount, users);

	}
}
