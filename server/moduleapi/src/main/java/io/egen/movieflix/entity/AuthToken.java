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
	@NamedQuery(name="AuthToken.findToken", query="FROM AuthToken t where t.authToken=:ah"),
	@NamedQuery(name="AuthToken.findByUserId", query="FROM AuthToken t where t.user.userId=:userId")
})
public class AuthToken implements Serializable {

	private static final long serialVersionUID = -3025056735527918652L;

	@Id
	@OneToOne(targetEntity = User.class)
	@JoinColumn(name="USER_ID")
	private User user;
	
	@Column(nullable=false)
	private String role;
	@Column(nullable=false)
	@GenericGenerator(name="movieflixUUID", strategy="uuid2")
	@GeneratedValue(generator="movieflixUUID")
	private String authToken;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getAuthToken() {
		return authToken;
	}
	
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	
}
