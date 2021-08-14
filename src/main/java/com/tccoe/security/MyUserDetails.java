package com.tccoe.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tccoe.entity.User;

public class MyUserDetails implements UserDetails {

	private String username;
	private String password;
	private int accountExpired;
	private int accountLocked;
	private int credentialsExpired;
	private int accountEnabled;
	private List<GrantedAuthority> authorities;
	
	public MyUserDetails(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.accountEnabled = user.getEnabled();
		this.accountExpired = user.getAccountExpired();
		this.accountLocked = user.getLocked();
		this.credentialsExpired = user.getPasswordExpired();
		
		
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getRole());
		List<GrantedAuthority> grantedAuthorityList = Arrays.asList(simpleGrantedAuthority);
		this.authorities = grantedAuthorityList;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return accountExpired == 0 ? true : false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return accountLocked == 0 ? true : false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return credentialsExpired == 0 ? true : false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return accountEnabled == 1 ? true : false;
	}

}
