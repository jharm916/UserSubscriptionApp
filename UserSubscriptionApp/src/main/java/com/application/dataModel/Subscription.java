package com.application.dataModel;

/**
 * A class representing a paid service a user is subscribed to
 * @author harman.jeremy
 *
 */
public class Subscription {
	
	/**
	 * id for Subscription object
	 */
	private String subscriptionId;
	
	/**
	 * description of paid Subscription
	 */
	private String description;
	
	/**
	 * monthly cost dollar amount for Subscription
	 */
	private double monthlyCost;
	
	public Subscription() {
		
	}
	
	/**
	 * Constructor for Subscription
	 * @param subscriptionId id to set, immutable
	 * @param description initial description value
	 * @param monthlyCost initial monthlyCost value
	 */
	public Subscription(String subscriptionId, String description, double monthlyCost) {
		this.subscriptionId = subscriptionId;
		this.description = description;
		this.monthlyCost = monthlyCost;
	}
	
	/**
	 * Getter for subscriptionId
	 * @return subscriptionId
	 */
	public String getSubscriptionId() {
		return this.subscriptionId;
	}
	
	/**
	 * Getter for description
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Setter for description
	 * @param description String for description assignment
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Getter for monthlyCost
	 * @return monthlyCost
	 */
	public double getMonthlyCost() {
		return this.monthlyCost;
	}
	
	/**
	 * Setter for monthlyCost
	 * @param monthlyCost double for monthlyCost assignment
	 */
	public void setMonthlyCost(double monthlyCost) {
		this.monthlyCost = monthlyCost;
	}
	
}