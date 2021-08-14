package com.tccoe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@ToString
@Setter
@Table(name = "USER")
@NamedQuery(name="User.findAll", query = "SELECT u FROM User u")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = false, length = 255)
	private String password;
	
	@Column(nullable = false, length = 255, name="CONFIRM_PASSWORD")
	private String confirmPassword;
	
	@Column(nullable = false, length = 50)
	private String username;
	
	@Column(nullable = false, length = 50)
	private String role;
	
	@Column(nullable = false, length = 1)
	private int locked;
	
	@Column(nullable = false, length = 1, name="ACCOUNT_EXPIRED")
	private int accountExpired;
	
	@Column(nullable = false, length = 1, name="PASSWORD_EXPIRED")
	private int passwordExpired;
	
	@Column(nullable = false, length = 1)
	private int enabled;

}
