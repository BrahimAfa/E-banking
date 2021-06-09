package com.ensas.ebanking.payload.response;

import com.ensas.ebanking.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private Set<Role> roles;
	private String email;
	private String fname;
	private String lname;
}
