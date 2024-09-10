package com.omfed.Services;

import java.util.List;
import java.util.Optional;

import com.omfed.Entities.User;

public interface userService {

	User createUser(User user);
	List<User> getAllUsers();
	User getUserById(String id);
	User updateUser(User user,String id);
	void deleteUser(String id);
	User getUserByEmail(String email);
}
