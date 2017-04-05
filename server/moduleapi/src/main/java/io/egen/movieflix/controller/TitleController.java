package io.egen.movieflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.movieflix.entity.Title;
import io.egen.movieflix.service.TitleService;


@RestController
public class TitleController {

	@Autowired
	private TitleService service;
	
	public TitleController(TitleService service) {
		this.service = service;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/catalog/{from}")
	public List<Title> getCatalog(@RequestHeader(value="ah") String authToken, @PathVariable("from") int fromCount) {
		return service.getCatalog(authToken, fromCount);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/catalog/topimdb/{type}/{from}")
	public List<Title> getTopIMDB(@RequestHeader(value="ah") String authToken, 
			@PathVariable("type") String type, 
			@PathVariable("from") int fromCount) {
		return service.getTopIMDB(authToken, type, fromCount);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/catalog/filter/{field}/{value}/{from}")
	public List<Title> getFilteredTitles(@RequestHeader(value="ah") String authToken, 
			@PathVariable("field") String field, 
			@PathVariable("value") String value,
			@PathVariable("from") int fromCount) {
		return service.getFilteredTitles(authToken, field, value, fromCount);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/catalog/sort/{order}/{field}/{from}")
	public List<Title> getSortedTitles(@RequestHeader(value="ah") String authToken, 
			@PathVariable("order") String sortOrder,
			@PathVariable("field") String field,
			@PathVariable("from") int fromCount) {
		return service.getSortedTitles(authToken, sortOrder, field, fromCount);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/title/details")
	public Title getDetails(@RequestHeader(value="ah") String authToken, String name) {
		return service.getDetails(authToken, name);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/title/rate")
	public void rateTitle(@RequestHeader(value="ah") String authToken, String titleId, String userId, int starRating) {
		service.rateTitle(authToken, titleId, userId, starRating);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/title/review")
	public void reviewTitle(@RequestHeader(value="ah") String authToken, String titleId, String userId, int userReview) {
		service.reviewTitle(authToken, titleId, userId, userReview);
	}
	
}
