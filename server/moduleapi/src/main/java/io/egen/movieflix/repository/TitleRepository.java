package io.egen.movieflix.repository;

import java.util.List;

import io.egen.movieflix.entity.Title;


public interface TitleRepository {
	
	public List<Title> getCatalog(int fromCount);
	
	public List<Title> getTopIMDB(String type, int fromCount);
	
	public List<Title> getFiltered(String field, String value, int fromCount);
	
	public List<Title> getSorted(String sortOrder, String field, int fromCount);

}
