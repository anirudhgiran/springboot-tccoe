package com.tccoe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tccoe.dto.JwtRequestDTO;
import com.tccoe.dto.JwtResponseDTO;
import com.tccoe.security.MyUserDetails;
import com.tccoe.security.MyUserService;
import com.tccoe.utils.JwtUtils;

@RestController
@CrossOrigin
public class JwtController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserService myUserService;

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/authenticate")
	public ResponseEntity<JwtResponseDTO> createAuthenticationToken(@RequestBody JwtRequestDTO jwtRequestDTO)
			throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequestDTO.getUsername(), jwtRequestDTO.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("User is disabled", e);
		} catch (BadCredentialsException e) {
			throw new Exception("Credentials are Invalid", e);
		} catch (Exception e) {
			throw new Exception("Something went wrong", e);
		}
		
		MyUserDetails myUserDetails = (MyUserDetails) myUserService.loadUserByUsername(jwtRequestDTO.getUsername());
		String token = jwtUtils.generateToken(myUserDetails);
		
		return ResponseEntity.ok(new JwtResponseDTO(token));
	}

}
