package io.egen.movieflix.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.movieflix.entity.User;
import io.egen.movieflix.exception.AuthorizationException;
import io.egen.movieflix.exception.UserAlreadyExistsException;
import io.egen.movieflix.exception.UserNotFoundException;
import io.egen.movieflix.repository.AuthTokenRepository;
import io.egen.movieflix.repository.UserRepository;
import io.egen.movieflix.service.UserService;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	@Autowired
	private AuthTokenRepository authrepository;

	public UserServiceImpl(UserRepository repository, AuthTokenRepository arepository) {
		this.repository = repository;
		this.authrepository = arepository;
	}

	@Override
	public List<User> findAll(String authToken) {
		if(authrepository.validateToken(authToken, true) != null)
			return repository.findAll();
		throw new AuthorizationException("Unauthorized access: User could not be authenticated");
	}

	@Override
	public User findOne(String authToken, String id) {
		if(authrepository.validateToken(authToken, true) != null)
			return repository.findOne(id);
		throw new AuthorizationException("Unauthorized access: User could not be authenticated");
	}

	@Override
	@Transactional
	public User create(String authToken, User user) {
		if(authrepository.validateToken(authToken, true) == null)
			throw new AuthorizationException("Unauthorized access: User could not be authenticated");
		User existing = repository.findByEmail(user.getEmail());
		if (existing != null) {
			throw new UserAlreadyExistsException("User with email "+user.getEmail()+" already exists");
		}
		user = repository.create(user);
		authrepository.addNewToken(user, "user");
		return user;
	}

	@Override
	@Transactional
	public User update(String authToken, String id, User user) {
		if(authrepository.validateToken(authToken, true) == null)
			throw new AuthorizationException("Unauthorized access: User could not be authenticated");
		User existing = repository.findOne(id);
		if (existing == null) {
			throw new UserNotFoundException("User with id "+id+" not found");
		}
		return repository.update(user);
	}

	@Override
	@Transactional
	public int delete(String authToken, String id) {
		if(authrepository.validateToken(authToken, true) == null)
			throw new AuthorizationException("Unauthorized access: User could not be authenticated");
		User existing = repository.findOne(id);
		if (existing == null) {
			throw new UserNotFoundException("User with id "+id+" not found");
		}
		return authrepository.removeToken(id) + repository.delete(existing);
	}
}