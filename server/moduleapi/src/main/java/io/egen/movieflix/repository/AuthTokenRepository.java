package io.egen.movieflix.repository;

import io.egen.movieflix.entity.AuthToken;
import io.egen.movieflix.entity.User;


public interface AuthTokenRepository {

	public User validateToken(String token, boolean checkForAdmin);
	
	public AuthToken addNewToken(User user, String role);
	
	public int removeToken(String userId);
}
