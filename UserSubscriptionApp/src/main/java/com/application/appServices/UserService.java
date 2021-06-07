package com.application.appServices;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Collections;

import com.application.dataModel.Subscription;
import com.application.dataModel.InitialData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.application.dataModel.Subscription;
import com.application.dataModel.User;

/**
 * A service for User related methods which can be consumed from our Rest Controller
 * @author harman.jeremy
 *
 */
@Component
public class UserService {
	
	@Autowired
	private InitialData data;
	
	/**
	 * Constructor for UserService
	 */
	public UserService(InitialData data) {
		this.data = data;
	}
	
	/**
	 * Gets the user with id userId
	 * @param userId id for User
	 * @return User object
	 */
	public User retrieveUser(String userId) {
		return data.users.get(userId);
	}
	
	/**
	 * Inserts or updates the User info associated with userId
	 * @param userId userId for user (must be unique)
	 * @param name name for user
	 * @param email email for user
	 * @return boolean if successful (non-duplicate)
	 */
	public boolean insertUser(String userId, String name, String email) {
		User user = new User(userId, name, email);
		return data.putUser(user);
	}
	
	
}
