package io.egen.movieflix.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.movieflix.repository.AuthTokenRepository;
import io.egen.movieflix.service.AuthTokenService;


@Service
public class AuthTokenServiceImpl implements AuthTokenService {

	@Autowired
	private AuthTokenRepository repository;
	
	public AuthTokenServiceImpl(AuthTokenRepository repository) {
		this.repository = repository;
	}

	@Override
	public boolean validateToken(String token) {
		return repository.validateToken(token);
	}
	
	
}
