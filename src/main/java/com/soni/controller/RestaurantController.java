package com.soni.controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soni.Exception.RestaurantException;
import com.soni.Exception.UserException;
import com.soni.domain.Role;
import com.soni.dto.RestaurantDto;
import com.soni.model.Restaurant;
import com.soni.model.User;
import com.soni.repository.RestaurantRepository;
import com.soni.repository.UserRepository;
import com.soni.request.CreateRestaurantRequest;
import com.soni.response.ApiResponse;
import com.soni.service.RestaurantService;
import com.soni.service.UserService;

@RestController
@RequestMapping("/api")
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private UserService userService;


	@GetMapping("/restaurant/search/{name}")
	public ResponseEntity<Restaurant> findRestaurantByName(@PathVariable String name) throws RestaurantException {
		Restaurant restaurant = restaurantService.findRestaurantByName(name);

		return ResponseEntity.ok(restaurant);
	}


	@GetMapping("/restaurants")
	public ResponseEntity<List<Restaurant>> getAllRestaurants() throws RestaurantException, UserException {

		List<Restaurant> restaurants = restaurantService.getAllRestaurant();
		
		
		return ResponseEntity.ok(restaurants);
	}
	
	
	@GetMapping("/restaurant/{id}")
	public ResponseEntity<Restaurant> findRestaurantById(@PathVariable Long id) throws RestaurantException, UserException {
		
	
			Restaurant restaurant = restaurantService.findRestaurantById(id);
			return ResponseEntity.ok(restaurant);
		
	
	}
	
	@PutMapping("/restaurant/{id}/add-favorites")
	public ResponseEntity<RestaurantDto> addToFavorite(
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id) throws RestaurantException, UserException {
		
		User user = userService.findUserProfileByJwt(jwt);
			RestaurantDto restaurant = restaurantService.addToFavorites(id, user);
			return ResponseEntity.ok(restaurant);
		
	
	}


}
