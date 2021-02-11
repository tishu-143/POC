package com.optum.pathway.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.optum.pathway.poc.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

}
