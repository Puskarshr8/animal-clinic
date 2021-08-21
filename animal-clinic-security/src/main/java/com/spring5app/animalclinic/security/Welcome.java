package com.spring5app.animalclinic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring5app.animalclinic.security.model.AuthenticationRequest;
import com.spring5app.animalclinic.security.model.AuthenticationResponse;
import com.spring5app.animalclinic.security.service.ApplicationUserDetailsService;
import com.spring5app.animalclinic.security.utility.JWTUtil;

@RestController
@RequestMapping(value = "/animalclinic/api")
public class Welcome {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private ApplicationUserDetailsService userService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request)
	{
		try
		{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("OHari","adacadabra"));
		}
		catch(BadCredentialsException e)
		{
			return new ResponseEntity<>(new AuthenticationResponse(null), HttpStatus.UNAUTHORIZED);
		}
		
		final UserDetails userDetails = userService.loadUserByUsername(request.getUserName());
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));		
		
	}
}
