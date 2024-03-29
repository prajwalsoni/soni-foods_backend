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
import com.soni.model.Restaurant;
import com.soni.model.User;
import com.soni.request.CreateRestaurantRequest;
import com.soni.response.ApiResponse;
import com.soni.service.RestaurantService;
import com.soni.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminRestaurantController {
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private UserService userService;

	@PostMapping("/restaurant")
	public ResponseEntity<Restaurant> createRestaurant(
			@RequestBody CreateRestaurantRequest req,
			@RequestHeader("Authorization") String jwt) throws UserException {
		User user = userService.findUserProfileByJwt(jwt);
		
//			System.out.println("----TRUE___-----");
			Restaurant restaurant = restaurantService.createRestaurant(req,user);
			return ResponseEntity.ok(restaurant);
		
	}


	@PutMapping("/restaurant/{id}")
	public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody CreateRestaurantRequest req,
			@RequestHeader("Authorization") String jwt) throws RestaurantException, UserException {
		User user = userService.findUserProfileByJwt(jwt);
		
			Restaurant restaurant = restaurantService.updateRestaurant(id, req);
			return ResponseEntity.ok(restaurant);
		
	}

	@DeleteMapping("/restaurant/{id}")
	public ResponseEntity<ApiResponse> deleteRestaurantById(@PathVariable("id") Long restaurantId,
			@RequestHeader("Authorization") String jwt) throws RestaurantException, UserException {
		User user = userService.findUserProfileByJwt(jwt);
		
			restaurantService.deleteRestaurant(restaurantId);
			
			ApiResponse res=new ApiResponse("Restaurant Deleted with id Successfully",true);
			return ResponseEntity.ok(res);
	
		
	}

	
	@GetMapping("/restaurants/user")
	public ResponseEntity<List<Restaurant>> getUsersRestaurant(@RequestHeader("Authorization") String jwt) throws UserException, RestaurantException {
		User user = userService.findUserProfileByJwt(jwt);
		List<Restaurant> restaurants = restaurantService.getRestaurantsByUserId(user.getId());
		return ResponseEntity.ok(restaurants);
	}
	
	
	

}