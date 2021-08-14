package com.tccoe.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tccoe.security.MyUserDetails;
import com.tccoe.security.MyUserService;
import com.tccoe.utils.JwtUtils;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private MyUserService myUserService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");
		String jwtToken;
		String username;
		
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			try {
				jwtToken = requestTokenHeader.substring(7);
				username = jwtUtils.getUsernameFromToken(jwtToken);
				
				if(username != null) {
					MyUserDetails myUserDetials = (MyUserDetails) myUserService.loadUserByUsername(username);
					
					if(jwtUtils.validateToken(jwtToken, myUserDetials)) {
						UsernamePasswordAuthenticationToken usernamePassowrdAuthenticationToken = 
								new UsernamePasswordAuthenticationToken(myUserDetials, null, myUserDetials.getAuthorities());
						
						SecurityContextHolder.getContext().setAuthentication(usernamePassowrdAuthenticationToken);
					}
				}
			}
			catch (Exception e) {
				System.out.println("Invalid");
			}
		}
		
		filterChain.doFilter(request, response);
		
	}

}
