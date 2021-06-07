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
 * A service for Subscription related methods which can be consumed from our Rest Controller
 * @author harman.jeremy
 *
 */
@Component
public class SubscriptionService {
	
	@Autowired
	private InitialData data;
	
	/**
	 * Constructor for SubscriptionService
	 */
	public SubscriptionService(InitialData data) {
		this.data = data;
	}
	
	/**
	 * Gets the subscription with id subscriptionId
	 * @param subscriptionId id for subscription
	 * @return Subscription object
	 */
	public Subscription retrieveSubscription(String subscriptionId) {
		return data.subscriptions.get(subscriptionId);
	}
	
	/**
	 * Retrieve subscriptions purchased by User with userId
	 * @param userId id for User
	 * @return list of Subscription objects
	 */
	public List<Subscription> retrieveSubscriptionsForUser(String userId) {
		// return map toCollection of subscriptionId list to Subscription objects
		List<Subscription> subs = data.userToSubscriptions.get(userId).stream()
				.map(id -> data.subscriptions.get(id)).collect(Collectors.toList());
		return subs;
	}
	
	/**
	 * Sorts subscriptions list for a given user
	 * If userId doesn't exist return false
	 */
	public boolean sortSubscriptions(String userId) {
		if (userId != null && data.userToSubscriptions.containsKey(userId)) {
			String[] subscriptions = data.userToSubscriptions.get(userId).toArray(new String[0]);
			Arrays.sort(subscriptions);
			data.userToSubscriptions.put(userId, Arrays.asList(subscriptions));
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Inserts or updates the Subscription info associated with subscriptionId
	 * @param subscriptionId subscriptionId for Subscription
	 * @param description description of Subscription service
	 * @param monthlyCost monthly cost of subscription
	 * @return true if added
	 */
	public boolean insertSubscription(String subscriptionId, String description, double monthlyCost) {
		Subscription sub = new Subscription(subscriptionId, description, monthlyCost);
		return data.putSubscription(sub);
	}
	
	/**
	 * Adds subscription id to list of subscriptions for user with userId
	 * @param userId userId to associate with subscription
	 * @param subscriptionId subscriptionId for subscription
	 * @return true if added
	 */
	public boolean insertSubscriptionForUser(String userId, String subscriptionId) {
		return data.putSubscriptionForUser(userId, subscriptionId);
	}
	
}
