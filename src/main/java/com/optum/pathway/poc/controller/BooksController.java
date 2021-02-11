package com.optum.pathway.poc.controller;

import com.optum.pathway.poc.service.GraphQLService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BooksController {
	@Autowired
	private GraphQLService graphQLService;

	@PostMapping("/getAllBooks")
	public ResponseEntity<Object> getAllBooks(@RequestBody String query) {
		ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);
		System.out.println("Get DATA = "+executionResult.getData().toString());
		System.out.println("TO Specification = "+executionResult.toSpecification());
		System.out.println("Get Extensions = "+executionResult.getExtensions());
		return new ResponseEntity<>(executionResult, HttpStatus.OK);
	}
}
