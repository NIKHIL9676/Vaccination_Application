package com.example.practise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Members")
public class Members {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String identity_type;
	private String identity_number;
	private String vaccine_name;
	private int no_of_doses;
	@ManyToOne
	private User user;

	public Members() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Members(int id, String name, String identity_type, String identity_number, String vaccine_name,
			int no_of_doses, User user) {
		super();
		this.id = id;
		this.name = name;
		this.identity_type = identity_type;
		this.identity_number = identity_number;
		this.vaccine_name = vaccine_name;
		this.no_of_doses = no_of_doses;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentity_type() {
		return identity_type;
	}

	public void setIdentity_type(String identity_type) {
		this.identity_type = identity_type;
	}

	public String getIdentity_number() {
		return identity_number;
	}

	public void setIdentity_number(String identity_number) {
		this.identity_number = identity_number;
	}

	public String getVaccine_name() {
		return vaccine_name;
	}

	public void setVaccine_name(String vaccine_name) {
		this.vaccine_name = vaccine_name;
	}

	public int getNo_of_doses() {
		return no_of_doses;
	}

	public void setNo_of_doses(int no_of_doses) {
		this.no_of_doses = no_of_doses;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Members [id=" + id + ", name=" + name + ", identity_type=" + identity_type + ", identity_number="
				+ identity_number + ", vaccine_name=" + vaccine_name + ", no_of_doses=" + no_of_doses + "]";
	}

}
