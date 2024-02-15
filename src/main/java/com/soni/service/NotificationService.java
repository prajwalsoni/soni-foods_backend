package com.soni.service;

import com.soni.model.Order; 
import com.soni.model.Restaurant;
import com.soni.model.User;

public interface NotificationService {
	
	public void sendOrderStatusNotification(User user, Order order);
	public void sendRestaurantNotification(Restaurant restaurant, String message);
	public void sendPromotionalNotification(User user, String message);

}
