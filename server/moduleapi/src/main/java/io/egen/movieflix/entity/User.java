package io.egen.movieflix.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table
@NamedQueries({
	@NamedQuery(name="User.findAll", query="SELECT u FROM User u ORDER BY u.firstName"),
	@NamedQuery(name="User.findByEmail", query="SELECT u FROM User u where u.email=:pEmail")
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
	@ManyToOne(targetEntity = Address.class)
	@JoinColumn(name="ADDRESS_ID")
	private Address address;
	
	
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}