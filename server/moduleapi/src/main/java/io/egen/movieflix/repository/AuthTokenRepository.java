package io.egen.movieflix.repository;

public interface AuthTokenRepository {

	public boolean validateToken(String token);
	
}
