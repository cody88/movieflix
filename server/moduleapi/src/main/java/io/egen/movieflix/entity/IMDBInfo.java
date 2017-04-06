package io.egen.movieflix.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class IMDBInfo {

	@Id
	@GenericGenerator(name="movieflixUUID", strategy="uuid2")
	@GeneratedValue(generator="movieflixUUID")
	@Column(name="IMDB_INFO_ID")
	private String imdbInfoId;
	
	@Column(nullable=false)
	private String imdbId;
	
	private double imdbRating;
	private int imdbVotes;
	
	public String getImdbInfoId() {
		return imdbInfoId;
	}
	
	public void setImdbInfoId(String imdbInfoId) {
		this.imdbInfoId = imdbInfoId;
	}
	
	public String getImdbId() {
		return imdbId;
	}
	
	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}
	
	public double getImdbRating() {
		return imdbRating;
	}
	
	public void setImdbRating(double imdbRating) {
		this.imdbRating = imdbRating;
	}
	
	public int getImdbVotes() {
		return imdbVotes;
	}
	
	public void setImdbVotes(int imdbVotes) {
		this.imdbVotes = imdbVotes;
	}
	
}
