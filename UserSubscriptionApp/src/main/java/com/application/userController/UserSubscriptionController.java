package com.application.userController;

import java.util.List;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.application.dataModel.User;
import com.application.dataModel.Subscription;
import com.application.appServices.UserService;
import com.application.appServices.SubscriptionService;

@RestController
public class UserSubscriptionController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@GetMapping("/users/{userId}")
	public User retrieveUser(@PathVariable String userId) {
		return userService.retrieveUser(userId);
	}
	
	@GetMapping("/users/{userId}/subscriptions")
	public List<Subscription> retrieveSubscriptionsForUser(@PathVariable String userId) {
		return subscriptionService.retrieveSubscriptionsForUser(userId);
	}
	
	@GetMapping("/users/{userId}/sortSubscriptions")
	public ResponseEntity<String> sortSubscriptions(@PathVariable String userId) {
		boolean sorted = subscriptionService.sortSubscriptions(userId);
		if (sorted)
			return ResponseEntity.ok("sorted");
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/users/put/{userId}/{name}/{email}")
	public ResponseEntity<String> insertUser(@PathVariable String userId, @PathVariable String name,
				@PathVariable String email) {
		boolean inserted = userService.insertUser(userId, name, email);
		if (inserted)
			return ResponseEntity.ok("added");
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/subscriptions/{subscriptionId}")
	public Subscription retrieveSubscription(@PathVariable String subscriptionId) {
		return subscriptionService.retrieveSubscription(subscriptionId);
	}
	
	@GetMapping("/subscriptions/put/{subscriptionId}/{description}/{monthlyCost}")
	public ResponseEntity<String> insertSubscription(@PathVariable String subscriptionId, @PathVariable String description,
				@PathVariable double monthlyCost) {
		boolean inserted = subscriptionService.insertSubscription(subscriptionId, description, monthlyCost);
		if (inserted)
			return ResponseEntity.ok("added");
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/subscriptions/subscriptionForUser/{userId}/{subscriptionId}")
	public ResponseEntity<String> insertSubscriptionForUser(@PathVariable String userId, @PathVariable String subscriptionId) {
		boolean inserted = subscriptionService.insertSubscriptionForUser(userId, subscriptionId);
		if (inserted)
			return ResponseEntity.ok("added");
		return ResponseEntity.ok("not added");
	}
}
