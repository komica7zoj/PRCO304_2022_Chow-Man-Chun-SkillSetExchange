package com.SkillSetExchange.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SkillSetExchange.jwt.JwtUtils;
import com.SkillSetExchange.models.SkillSetType;
import com.SkillSetExchange.models.UserMultichainContent;
import com.SkillSetExchange.models.DAO.CreditUnitDAO;
import com.SkillSetExchange.models.DAO.SearchConditionDAO;
import com.SkillSetExchange.repository.MultichainRepository;
import com.SkillSetExchange.services.MultiChainService;
import com.SkillSetExchange.services.SearchSkillSetService;
import com.SkillSetExchange.services.impl.UserDetailsServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/search")
public class SearchSkillSetController {
	  @Autowired
	  private JwtUtils jwtUtils;

	  @Autowired
	  private UserDetailsServiceImpl userDetailsService;
	
	  @Autowired
	  private MultichainRepository multichainRepository;
	  
	  @Autowired
	  private MultiChainService multiChainService;
	  
	  @Autowired
	  SearchSkillSetService searchSkillSetService;
	  
		@PostMapping("/getSkillSetList")
		@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
		public SearchConditionDAO GetTackleList(@RequestBody Map<String, Object> dao) throws IOException {
			ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature .USE_LONG_FOR_INTS, true);;
			SearchConditionDAO	searchConditionDAO =  objectMapper.convertValue(dao.get("searchConditionDAO"), SearchConditionDAO.class);
			if(! searchConditionDAO.skillSetCategory.isEmpty())
			{
				return searchSkillSetService.findSkillSet(searchConditionDAO);	
			}
			return null;
			}
	  
	  
	@PostMapping("/findskillset")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public SearchConditionDAO FindSkillSet(@RequestBody Map<String, Object> dao) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature .USE_LONG_FOR_INTS, true);;
		SearchConditionDAO	searchConditionDAO =  objectMapper.convertValue(dao.get("searchConditionDAO"), SearchConditionDAO.class);
		if(! searchConditionDAO.skillSetType.isEmpty())
		{
			return searchSkillSetService.getSkillSetCategory(searchConditionDAO);	
		}
		return null;
		}
	
	@GetMapping("/getskillsetpool")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public SearchConditionDAO GetSkillSetPool() {

		  
		
	        return searchSkillSetService.getAllSkillSetType();
	}
	
}
