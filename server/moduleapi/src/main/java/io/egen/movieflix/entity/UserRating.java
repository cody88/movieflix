package io.egen.movieflix.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table
@NamedQueries({
	@NamedQuery(name="UserRating.findRating", query="SELECT u FROM UserRating u where u.titleId=:ptitleId and u.user.userId=:puserId"),
	@NamedQuery(name="UserRating.findAllRatingByUserId", query="SELECT u FROM UserRating u where u.user.userId=:puserId")
})
public class UserRating implements Serializable {

	private static final long serialVersionUID = 2465960212839344527L;

	@Id
	@GenericGenerator(name="movieflixUUID", strategy="uuid2")
	@GeneratedValue(generator="movieflixUUID")
	@Column(name="USER_RATING_ID")
	private String userRatingId;
	
	@OneToOne(targetEntity = User.class)
	@JoinColumn(name="USER", referencedColumnName="USER_ID", nullable=false)
	private User user;
	//@ManyToOne(targetEntity = Title.class, cascade = CascadeType.ALL)
	//@JoinColumn(name="TITLEID", referencedColumnName="TITLE_ID", nullable=false)
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
