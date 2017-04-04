package io.egen.movieflix.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.movieflix.entity.User;
import io.egen.movieflix.repository.UserRepository;
import io.egen.movieflix.service.UserService;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<User> findAll() {
		return repository.findAll();
	}

	@Override
	public User findOne(String id) {
		return repository.findOne(id);
	}

	@Override
	@Transactional
	public User create(User user) {
		User existing = repository.findByEmail(user.getEmail());
		if (existing != null) {
			// throw new Exception
		}
		return repository.create(user);
	}

	@Override
	@Transactional
	public User update(String id, User user) {
		User existing = repository.findOne(id);
		if (existing != null) {
			// throw new Exception
		}
		return repository.update(user);
	}

	@Override
	@Transactional
	public void delete(String id) {
		User existing = repository.findOne(id);
		if (existing != null) {
			// throw new Exception
		}
		repository.delete(existing);
	}
}