package com.soni.service;

import java.time.LocalDateTime; 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soni.Exception.ReviewException;
import com.soni.model.Restaurant;
import com.soni.model.Review;
import com.soni.model.User;
import com.soni.repository.RestaurantRepository;
import com.soni.repository.ReviewRepository;
import com.soni.request.ReviewRequest;
@Service
public class ReviewServiceImplementation implements ReviewSerive {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

   @Override
    public Review submitReview(ReviewRequest reviewRequest, User user) {
        Review review = new Review();
        System.out.println(reviewRequest);
        
        System.out.println(reviewRequest.getRestaurantId());
         Optional<Restaurant> restaurant = restaurantRepository.findById(reviewRequest.getRestaurantId());
         if(restaurant.isPresent()) {
        	 review.setRestaurant(restaurant.get());
         }
        review.setCustomer(user);
        review.setReviewText(reviewRequest.getReviewText());
        review.setRating(reviewRequest.getRating());
        review.setCreatedAt(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Long reviewId, ReviewRequest updatedReview) throws ReviewException {
  
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);

        if (optionalReview.isPresent()) {
       
            Review review = optionalReview.get();
            review.setReviewText(updatedReview.getReviewText());
           System.out.println(review);
            return reviewRepository.save(review);
        } else {
            throw new ReviewException("Review with ID " + reviewId + " not found");
        }
    }

    @Override
    public void deleteReview(Long reviewId) throws ReviewException {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);

        if (optionalReview.isPresent()) {
            reviewRepository.deleteById(reviewId);
        } else {
            throw new ReviewException("Review with ID " + reviewId + " not found");
        }
    }

    @Override
    public double calculateAverageRating(List<Review> reviews) {
    	 double totalRating = 0;

         for (Review review : reviews) {
             totalRating += review.getRating();
         }

         if (reviews.size() > 0) {
             return totalRating / reviews.size();
         } else {
             return 0;
         }
    }
}

