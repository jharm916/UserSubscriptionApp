package com.application.dataModel;

/**
 * A class representing a user
 * @author harman.jeremy
 *
 */
public class User {

	/**
	 * id for User object. Represent with a user-name type field
	 */
	private String userId;
	
	/**
	 * full name of User
	 */
	private String name;
	
	/**
	 * email address of user
	 */
	private String email;
	
	public User() {
		
	}
	
	/**
	 * Constructor for User
	 * @param userId id to set, immutable
	 * @param name initial name value
	 * @param address initial email value
	 */
	public User(String userId, String name, String email) {
		this.userId = userId;
		this.name = name;
		this.email = email;
	}
	
	/**
	 * Getter for userId
	 * @return userId
	 */
	public String getUserId() {
		return this.userId;
	}
	
	/**
	 * Getter for name
	 * @return User's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Setter for name
	 * @param name String for name assignment
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for email
	 * @return User's email
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * Setter for email
	 * @param email String for email assignment
	 */
	public void setAddress(String email) {
		this.email = email;
	}
	
}
