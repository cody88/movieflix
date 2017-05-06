package io.egen.movieflix.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table
@NamedQueries({
	@NamedQuery(name="User.findAll", query="SELECT u FROM User u ORDER BY u.firstName"),
	@NamedQuery(name="User.findByEmail", query="SELECT u FROM User u where u.email=:pEmail"),
	@NamedQuery(name="User.findById", query="SELECT u FROM User u where u.userId=:puserId")
})
public class User implements Serializable {

	private static final long serialVersionUID = -4546378706290995683L;

	@Id
	@GenericGenerator(name="movieflixUUID", strategy="uuid2")
	@GeneratedValue(generator="movieflixUUID")
	@Column(name="USER_ID")
	private String userId;
	
	private String firstName;
	private String lastName;
	@Column(unique = true, nullable=false)
	private String email;
	private String street1;
	private String street2;
	private String city;
	private String state;
	private String zipcode;
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}