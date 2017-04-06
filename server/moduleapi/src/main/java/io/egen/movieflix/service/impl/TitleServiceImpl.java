package io.egen.movieflix.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.movieflix.entity.Title;
import io.egen.movieflix.entity.User;
import io.egen.movieflix.exception.AuthorizationException;
import io.egen.movieflix.exception.BadRequestException;
import io.egen.movieflix.repository.AuthTokenRepository;
import io.egen.movieflix.repository.TitleRepository;
import io.egen.movieflix.service.TitleService;


@Service
public class TitleServiceImpl implements TitleService {

	@Autowired
	private TitleRepository repository;
	@Autowired
	private AuthTokenRepository authrepository;
	
	public TitleServiceImpl(TitleRepository repository) {
		this.repository = repository;
	}
	
	@Override
	@Transactional
	public List<Title> getCatalog(String authToken, int fromCount) {
		if(authrepository.validateToken(authToken, false) != null)
			return repository.getCatalog(fromCount);
		throw new AuthorizationException("Unauthorized access: User could not be authenticated");
	}

	@Override
	@Transactional
	public List<Title> getTopIMDB(String authToken, String type, int fromCount) {
		if(authrepository.validateToken(authToken, false) != null)
			if(!(type.equals("movie") || type.equals("series")))
				throw new BadRequestException("Error in Request paratemers. Please check request again");
			else
				return repository.getTopIMDB(type, fromCount);
		throw new AuthorizationException("Unauthorized access: User could not be authenticated");
	}

	@Override
	@Transactional
	public List<Title> getFilteredTitles(String authToken, String field, String value, int fromCount) {
		if(authrepository.validateToken(authToken, false) != null)
			return repository.getFiltered(field, value, fromCount);
		throw new AuthorizationException("Unauthorized access: User could not be authenticated");
	}

	@Override
	@Transactional
	public List<Title> getSortedTitles(String authToken, String sortOrder, String field, int fromCount) {
		if(authrepository.validateToken(authToken, false) != null)
			return repository.getSorted(sortOrder, field, fromCount);
		throw new AuthorizationException("Unauthorized access: User could not be authenticated");
	}

	@Override
	@Transactional
	public Title getDetails(String authToken, String titleId) {
		if(authrepository.validateToken(authToken, false) != null)
			return repository.getDetails(titleId);
		throw new AuthorizationException("Unauthorized access: User could not be authenticated");
	}

	@Override
	@Transactional
	public int rateTitle(String authToken, String titleId, int starRating) {
		User usr = null;
		if((usr = authrepository.validateToken(authToken, false)) != null)
			return repository.rateTitle(usr.getUserId(), titleId, starRating);
		else
			throw new AuthorizationException("Unauthorized access: User could not be authenticated");
	}

	@Override
	@Transactional
	public int reviewTitle(String authToken, String titleId, String userReview) {
		User usr = null;
		if((usr = authrepository.validateToken(authToken, false)) != null)
			return repository.reviewTitle(usr.getUserId(), titleId, userReview);
		else
			throw new AuthorizationException("Unauthorized access: User could not be authenticated");		
	}

	@Override
	@Transactional
	public int addNewTitle(String authToken, Title title) {
		if(authrepository.validateToken(authToken, true) != null)
			return repository.addNewTitle(title);
		throw new AuthorizationException("Unauthorized access: Only admins can do this operation");
	}

	@Override
	@Transactional
	public int deleteTitle(String authToken, String titleId) {
		if(authrepository.validateToken(authToken, true) != null)
			return repository.deleteTitle(titleId);
		throw new AuthorizationException("Unauthorized access: Only admins can do this operation");
	}

	
}
