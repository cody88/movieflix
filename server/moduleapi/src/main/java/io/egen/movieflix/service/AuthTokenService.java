package io.egen.movieflix.service;

import io.egen.movieflix.entity.User;


public interface AuthTokenService {
	
	public User validateToken(String token, boolean checkForAdmin);

}
