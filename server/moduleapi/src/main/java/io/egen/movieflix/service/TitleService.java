package io.egen.movieflix.service;

import java.util.List;

import io.egen.movieflix.entity.Title;


public interface TitleService {
	
	public List<Title> getCatalog(String authToken, int fromCount);
	
	public List<Title> getTopIMDB(String authToken, String type, int fromCount);
	
	public List<Title> getFilteredTitles(String authToken, String field, String value, int fromCount);
	
	public List<Title> getSortedTitles(String authToken, String sortOrder, String field, int fromCount);
	
	public Title getDetails(String authToken, String name);
	
	public void rateTitle(String authToken, String titleId, String userId, int starRating);
	
	public void reviewTitle(String authToken, String titleId, String userId, int userReview);
	
	

}
