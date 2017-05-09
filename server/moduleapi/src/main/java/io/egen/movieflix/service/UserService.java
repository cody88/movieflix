package io.egen.movieflix.service;

import java.util.List;
import io.egen.movieflix.entity.User;


public interface UserService {

	public List<User> findAll(String authToken);

	public User findOne(String authToken, String id);

	public User create(String authToken, User user);

	public User update(String authToken, String id, User user);

	public int delete(String authToken, String id);
}