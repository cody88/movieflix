package io.egen.movieflix.repository;

import io.egen.movieflix.entity.User;


public interface AuthTokenRepository {

	public User validateToken(String token, boolean checkForAdmin);
	
}
