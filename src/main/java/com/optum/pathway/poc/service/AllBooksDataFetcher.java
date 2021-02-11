package com.optum.pathway.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optum.pathway.poc.model.Book;
import com.optum.pathway.poc.repository.BookRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Service
public class AllBooksDataFetcher implements DataFetcher<List<Book>>{

	@Autowired
	private BookRepository bookRepository;
	@Override
	public List<Book> get(DataFetchingEnvironment environment) {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	}

}
