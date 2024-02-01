package com.example.practise.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private int id;
	private String name;
	private String email;
	private String password;
	private String role;
	private boolean enabled; 
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Members> members;


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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Members> getMembers() {
		return members;
	}

	public void setMembers(List<Members> members) {
		this.members = members;
	}

	public User() {
		super();
	}

	public User(int id, String name, String email, String password, String role, boolean enabled,
			List<Members> members) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.enabled = enabled;
		this.members = members;
	}

	@Override
	public String toString() {
	    return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
	            + ", role=" + role + ", enabled=" + enabled + "]";
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}	
}
