package com.ensas.ebanking.controllers;

import com.ensas.ebanking.exceptions.ClientValidationException;
import com.ensas.ebanking.models.Role;
import com.ensas.ebanking.models.User;
import com.ensas.ebanking.payload.request.LoginRequest;
import com.ensas.ebanking.payload.response.JwtResponse;
import com.ensas.ebanking.payload.response.MessageResponse;
import com.ensas.ebanking.security.jwt.JwtUtils;
import com.ensas.ebanking.security.services.UserDetailsImpl;
import com.ensas.ebanking.services.UserService;
import com.ensas.ebanking.utils.AbstractConverter;
import com.ensas.ebanking.utils.ModelValidator;
import com.ensas.ebanking.vo.ClientVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


	// see web security
	// function that returns AuthenticationManager
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	UserService userService;

	@Autowired
	@Qualifier("clientConverter")
	private AbstractConverter<User, ClientVo> clientConverter;

	@PostMapping
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication;
		try{
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		}
		catch(BadCredentialsException ex){
				return ResponseEntity.badRequest().body("Email Or Password Incorrect");
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		String jwt = jwtUtils.generateJwtToken(userDetails);
		
		Set<Role> roles = userDetails.getUser().getRoles();

		return ResponseEntity.ok(new JwtResponse(jwt,"Bearer",userDetails.getId(),
				userDetails.getUsername(),
				roles,
				userDetails.getEmail(),
				userDetails.getUser().getFirstname(),
				userDetails.getUser().getLastname()));
	}
}
