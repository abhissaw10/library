package com.example.library.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.library.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String>{

	public List<Book> findByAuthor(String author);
}
