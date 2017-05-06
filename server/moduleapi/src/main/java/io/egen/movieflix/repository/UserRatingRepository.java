package io.egen.movieflix.repository;

import io.egen.movieflix.entity.UserRating;


public interface UserRatingRepository {

	public UserRating getUserRating(String titleId, String userId);
	
	public UserRating addUserRating(UserRating rating);
	
	public UserRating updateUserRating(UserRating rating);
	
}
