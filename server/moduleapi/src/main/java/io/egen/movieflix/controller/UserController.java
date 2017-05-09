package io.egen.movieflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.movieflix.entity.User;
import io.egen.movieflix.service.UserService;



@RestController
@RequestMapping(path = "user")
public class UserController {

	@Autowired
	private UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/all")
	public List<User> findAll(@RequestHeader(value="ah") String authToken) {
		return service.findAll(authToken);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public User findOne(@RequestHeader(value="ah") String authToken, @PathVariable("id") String id) {
		return service.findOne(authToken, id);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/add", consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public User create(@RequestHeader(value="ah") String authToken, @RequestBody User user) {
		return service.create(authToken, user);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/{id}/update", consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public User update(@RequestHeader(value="ah") String authToken, @PathVariable("id") String id, @RequestBody User user) {
		return service.update(authToken, id, user);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}/delete")
	public int delete(@RequestHeader(value="ah") String authToken, @PathVariable("id") String id) {
		return service.delete(authToken, id);
	}
}