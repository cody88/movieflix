package io.egen.movieflix.service;

import io.egen.movieflix.entity.UserRating;


public interface UserRatingService {

	public UserRating getUserRating(String authToken, String titleId, String userId);
	
	public UserRating addUserRating(String authToken, UserRating rating);
	
	public UserRating updateUserRating(String authToken, UserRating rating);
	
}
