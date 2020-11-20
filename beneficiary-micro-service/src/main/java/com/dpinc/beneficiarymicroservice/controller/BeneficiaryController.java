package com.dpinc.beneficiarymicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dpinc.beneficiarymicroservice.service.BeneficiaryService;

import graphql.ExecutionResult;

@RequestMapping("/dpinc/beneficiary")
@RestController
public class BeneficiaryController {

	@Autowired
	private BeneficiaryService beneficiaryService;
	
	@GetMapping("/health")
	public ResponseEntity<String> getHealth() {
		return new ResponseEntity<String>("Successful health check - Beneficiary Service with GraphQL", HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> getAllBooks(@RequestBody String query) {
		
		ExecutionResult execute = beneficiaryService.getGraphQL().execute(query);
		
		return new ResponseEntity<>(execute, HttpStatus.OK);
	}
	
	
}
