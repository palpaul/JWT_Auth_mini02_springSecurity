package com.jwt.app.Securitycontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.app.security.AuthenticateRequest;
import com.jwt.app.security.JwtUtil;
import com.jwt.app.security.MyUserDetailsService;


@RestController
public class Securityntroller {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtils;
	
	@GetMapping("/welcome")
	public String Welcome() {
		return "Welcome to Springboot Security websecurityConfigurationAdapter";
	}
	
	@PostMapping("/authenticate")
	public String authenticateUser(@RequestBody AuthenticateRequest request) throws Exception{
		try{
			authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		}catch(Exception e) {
			throw new Exception("Invalid Credentials");
		}
		
		UserDetails userDetials = myUserDetailsService.loadUserByUsername(request.getUsername());
		String token = jwtUtils.generateToken(userDetials);
		return token;
	}
}
