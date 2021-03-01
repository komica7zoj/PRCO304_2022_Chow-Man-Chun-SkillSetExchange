package com.SkillSetExchange.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SkillSetExchange.jwt.JwtUtils;
import com.SkillSetExchange.repository.MultichainRepository;
import com.SkillSetExchange.services.impl.UserDetailsServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/main")
public class MainBoradRouteController {

	  @Autowired
	  private JwtUtils jwtUtils;

	  @Autowired
	  private UserDetailsServiceImpl userDetailsService;
	
	  @Autowired
	  private MultichainRepository multichainRepository;
	  
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
	        String username = jwtUtils.getUserNameFromJwtToken(headerAuth.substring(7, headerAuth.length()));
	        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	      
		return "User Content. :"+userDetails.getUsername() + ":{"+multichainRepository.getUserMultichainContent(username).toString()+"}";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
