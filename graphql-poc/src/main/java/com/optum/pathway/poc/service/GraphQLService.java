package com.optum.pathway.poc.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import com.optum.pathway.poc.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {

	@Value("classpath:books.graphql")
	Resource resource;
	@Autowired
	private BookRepository bookRepository;

	private GraphQL graphQL;

	@Autowired
	private AllBooksDataFetcher allBooksDataFetcher;

	@Autowired
	private BookDataFetcher bookDataFetcher;
	
	//load schema at application start up
	@PostConstruct
	private void loadSchema() throws IOException {
		// Get the schema
		File graphQlSchemaFile = resource.getFile();
		// Parse Schema
		TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(graphQlSchemaFile);
		RuntimeWiring runtimeWiring = buildRuntimeWiring();
		GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
		graphQL = GraphQL.newGraphQL(graphQLSchema).build();
	}


	private RuntimeWiring buildRuntimeWiring() {
		// TODO Auto-generated method stub
		return RuntimeWiring.newRuntimeWiring()
				.type("Query", typeWiring->
					typeWiring.dataFetcher("allBooks", allBooksDataFetcher)
					.dataFetcher("book", bookDataFetcher)
		).build();
	}

	public GraphQL getGraphQL() {
		return graphQL;
	}
}
