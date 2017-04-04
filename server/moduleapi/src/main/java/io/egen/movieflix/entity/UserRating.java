package io.egen.movieflix.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table
public class UserRating {

	@Id
	@GenericGenerator(name="movieflixUUID", strategy="uuid2")
	@GeneratedValue(generator="movieflixUUID")
	@Column(name="USER_RATING_ID")
	private String userRatingId;
	
	@OneToOne
	@JoinColumn(name="USER_ID", referencedColumnName="USER_RATING_ID", nullable=false)
	private String userId;
	@JoinColumn(name="TITLE_ID", referencedColumnName="USER_RATING_ID", nullable=false)
	private String titleId;
	private int starRating;
	@Column(length=1000)
	private String userReview;
	
	
	public String getUserRatingId() {
		return userRatingId;
	}
	
	public void setUserRatingId(String userRatingId) {
		this.userRatingId = userRatingId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitleId() {
		return titleId;
	}

	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}

	public int getStarRating() {
		return starRating;
	}
	
	public void setStarRating(int starRating) {
		this.starRating = starRating;
	}
	
	public String getUserReview() {
		return userReview;
	}
	
	public void setUserReview(String userReview) {
		this.userReview = userReview;
	}
	
}
