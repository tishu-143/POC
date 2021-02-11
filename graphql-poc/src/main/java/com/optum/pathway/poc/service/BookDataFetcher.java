package com.optum.pathway.poc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optum.pathway.poc.model.Book;
import com.optum.pathway.poc.repository.BookRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Service
public class BookDataFetcher implements DataFetcher<Optional<Book>> {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Optional<Book> get(DataFetchingEnvironment environment) {
		// TODO Auto-generated method stub
		int isn = environment.getArgument("id");
		return bookRepository.findById(isn);
	}

}
