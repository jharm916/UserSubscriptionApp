package com.application.dataModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

/**
 * A class to store some data for the application
 * @author harman.jeremy
 *
 */
@Component
public class InitialData {

	/**
	 * Map subscriptionId to Subscription
	 */
	public Map<String, Subscription> subscriptions;
	
	/**
	 * Map userId to User
	 */
	public Map<String, User> users;
	
	/**
	 * Map userId to List of subscriptionIds
	 */
	public Map<String, List<String>> userToSubscriptions;
	
	/**
	 * Some userIds to insert into our data structure, these can be unique user-names
	 * for users
	 */
	private String[] userIds = new String[] {
			"JohnMadden1",
			"MikeyJones54",
			"FortressKing17",
			"FlowerGirl77",
			"FlyBoy101",
			"DogLover85"
	};
	
	/**
	 * Some subscriptionIds, these are unique online subscription-based services
	 */
	private String[] subscriptionIds = new String[] {
			"Hulu",
			"Netflix",
			"Amazon Prime",
			"Xbox Live",
			"LeetCode"
	};
	
	/**
	 * Constructor for initial data, just initialize some data
	 * the application can use.
	 */
	public InitialData() {
		this.subscriptions = new HashMap<>();
		this.users = new HashMap<>();
		this.userToSubscriptions = new HashMap<>();
		
		String inputUserData = "build\\resources\\main\\UserData.json";
		File userData = new File(inputUserData);
		String inputSubscriptionData = "build\\resources\\main\\SubscriptionData.json";
		File subscriptionData = new File(inputSubscriptionData);
		String inputUserSubscriptions = "build\\resources\\main\\UserSubscriptionData.json";
		File userSubscriptionData = new File(inputUserSubscriptions);
		
        if (userData.exists() && subscriptionData.exists() && userSubscriptionData.exists()){
        	try {
        		ObjectMapper mapper = new ObjectMapper();
        		InputStream is = new FileInputStream(inputUserData);
                String jsonTxt = IOUtils.toString(is, "UTF-8");
                Set<User> users = fromJSON(new TypeReference<Set<User>>() {}, jsonTxt, mapper);
                is.close();
                ;
                is = new FileInputStream(inputSubscriptionData);
                jsonTxt = IOUtils.toString(is, "UTF-8");
                Set<Subscription> subscriptions = fromJSON(new TypeReference<Set<Subscription>>() {}, jsonTxt, mapper);
                is.close();
                
                is = new FileInputStream(userSubscriptionData);
                jsonTxt = IOUtils.toString(is, "UTF-8");
                Set<UserSubscriptions> userSubscriptions = fromJSON(new TypeReference<Set<UserSubscriptions>>() {}, jsonTxt, mapper);
                is.close();
                
                // Insert objects
                for (User user : users) {
                	putUser(user);
                }
                for (Subscription subscription : subscriptions) {
                	putSubscription(subscription);
                }
                for (UserSubscriptions us : userSubscriptions) {
                	String userId = us.getUserId();
                	List<String> userSubs = us.getSubscriptions();
                	for (String subscription : userSubs) {
                		putSubscriptionForUser(userId, subscription);
                	}
                }
                
        	} catch (Exception e) {
        		System.out.println("Unable to parse " + e.toString());
        	}
        } else {
        	// Manually add some phony data
        	for (String id : userIds) {
    			putUser(new User(id, "", ""));
    		}
    		for (String id : subscriptionIds) {
    			putSubscription(new Subscription(id, "", 10.0));
    		}
    		String userId = userIds[0];
    		for (String id : subscriptionIds) {
    			putSubscriptionForUser(userId, id);
    		}
        }
	}
	
	/*
	 * Deserialize json into POJO
	 */
	public <T> T fromJSON(final TypeReference<T> type,
			final String jsonPacket, ObjectMapper mapper) {
	   T data = null;
	   try {
	      data = mapper.readValue(jsonPacket, type);
	   } catch (Exception e) {
		   System.out.println("Could not parse " + e.toString());
	   }
	   return data;
	}
	
	/**
	 * Adds or updates an entry to subscriptions
	 * @param subscription the Subscription object to put
	 * @return true if updated map, false if id is not set
	 */
	public boolean putSubscription(Subscription subscription) {
		String id = subscription.getSubscriptionId();
		if (id != null && !id.isEmpty()) {
			this.subscriptions.put(id, subscription);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Adds or updates an entry to users
	 * @param user the User object to put
	 * @return true if updated map, false if id is not set
	 */
	public boolean putUser(User user) {
		String id = user.getUserId();
		if (id != null && !id.isEmpty()) {
			this.users.put(id, user);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Inserts an entry into userToSubscriptions if both user and subscription
	 * have already been stored
	 * @param userId the userId
	 * @param subscriptionId the subscriptionId to add for user with userId
	 * @return
	 */
	public boolean putSubscriptionForUser(String userId, String subscriptionId) {
		if (userId != null && subscriptionId != null) {
			if (!users.containsKey(userId) || !subscriptions.containsKey(subscriptionId)) {
				return false;
			}
			if (this.userToSubscriptions.containsKey(userId)) {
				List<String> subscribed = this.userToSubscriptions.get(userId);
				if (subscribed == null) {
					subscribed = new ArrayList<String>();
				}
				if (!subscribed.contains(subscriptionId)) {
					subscribed.add(subscriptionId);
					// note this can result in an unsorted list
					this.userToSubscriptions.put(userId, subscribed);
				} else {
					return false; // handle duplicate by not adding it
				}
			} else {
				List<String> newList = new ArrayList<String>();
				newList.add(subscriptionId);
				this.userToSubscriptions.put(userId, newList);
			}
		} else {
			return false;
		}
		return true;
	}
	
}
