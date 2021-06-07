package com.application.dataModel;

import java.util.List;

/**
 * Class representing a mapping between a User and a list of Subscriptions
 * @author harman.jeremy
 *
 */
public class UserSubscriptions {
	
	/**
	 * id for User object
	 */
	private String userId;
	
	/**
	 * List of subscriptions for user as subscriptionIds
	 */
	private List<String> subscriptions;
	
	public UserSubscriptions() {
		
	}
	
	public UserSubscriptions(String userId, List<String> subscriptions) {
		this.userId = userId;
		this.subscriptions = subscriptions;
	}
	
	/**
	 * Getter for userId
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * Getter for subscriptions list
	 * @return List of subscription ids for this user
	 */
	public List<String> getSubscriptions() {
		return subscriptions;
	}
	
	/**
	 * Setter for subscriptions list
	 * @param subscriptions list to set subscriptions
	 */
	public void setSubscriptions(List<String> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
}
