package io.egen.movieflix.repository;

import java.util.List;

import io.egen.movieflix.entity.Title;


public interface TitleRepository {
	
	public void entEvict(String titleId); 
	
	public List<Title> getCatalog(int fromCount);
	
	public List<Title> getTopIMDB(String type, int fromCount);
	
	public List<Title> getFiltered(String field, String value, int fromCount);
	
	public List<Title> getSorted(String sortOrder, String field, int fromCount);
	
	public Title getDetails(String titleId);
	
	public int rateTitle(String userId, String titleId, int starRating);
	
	public int reviewTitle(String userId, String titleId, String userReview);
	
	public int addNewTitle(Title title);
	
	public int deleteTitle(String titleId);

}
