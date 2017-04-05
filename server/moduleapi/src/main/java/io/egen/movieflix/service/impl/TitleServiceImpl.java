package io.egen.movieflix.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.movieflix.entity.Title;
import io.egen.movieflix.exception.AuthorizationException;
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
		if(authrepository.validateToken(authToken))
			return repository.getCatalog(fromCount);
		throw new AuthorizationException("Unauthorized access: User could not be authenticated");
	}

	@Override
	public List<Title> getTopIMDB(String authToken, String type, int fromCount) {
		if(authrepository.validateToken(authToken))
			return repository.getTopIMDB(type, fromCount);
		throw new AuthorizationException("Unauthorized access: User could not be authenticated");
	}

	@Override
	public List<Title> getFilteredTitles(String authToken, String field, String value, int fromCount) {
		if(authrepository.validateToken(authToken))
			return repository.getFiltered(field, value, fromCount);
		throw new AuthorizationException("Unauthorized access: User could not be authenticated");
	}

	@Override
	public List<Title> getSortedTitles(String authToken, String sortOrder, String field, int fromCount) {
		if(authrepository.validateToken(authToken))
			return repository.getSorted(sortOrder, field, fromCount);
		throw new AuthorizationException("Unauthorized access: User could not be authenticated");
	}

	@Override
	public Title getDetails(String authToken, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void rateTitle(String authToken, String titleId, String userId, int starRating) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void reviewTitle(String authToken, String titleId, String userId, int userReview) {
		// TODO Auto-generated method stub
		
	}

	
}
