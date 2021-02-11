package com.optum.pathway.poc.service;

import com.optum.pathway.poc.model.Book;
import com.optum.pathway.poc.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateBookDataFetcher implements DataFetcher<Book> {

   @Autowired
    private BookRepository bookRepository;
    @Override
    public Book get(DataFetchingEnvironment dataFetchingEnvironment) {
        String title = dataFetchingEnvironment.getArgument("title");
        String publisher = dataFetchingEnvironment.getArgument("publisher");
        String authors = dataFetchingEnvironment.getArgument("authors");
        String publishedDate = dataFetchingEnvironment.getArgument("publishedDate");
        return bookRepository.saveAndFlush(new Book(title, publisher, authors, publishedDate));
    }
}
