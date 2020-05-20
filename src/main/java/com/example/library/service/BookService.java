package com.example.library.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;

@Service
public class BookService {
	
	Logger log = LoggerFactory.getLogger(BookService.class);

	@Autowired
	BookRepository bookRepo;
	
	/**
	 * Create a new Book
	 * @param b
	 * @return
	 * @throws Exception
	 */
	public Book create(Book b) throws Exception {
		log.debug("Entering create method for book "+b.getBookName());
		b.setId(UUID.randomUUID().toString());
		return bookRepo.save(b);
	}
	
	/**
	 * Search by Author name
	 * @param author
	 * @return
	 * @throws Exception
	 */
	public Optional<List<Book>> findByAuthor(String author) throws Exception{
		List books = bookRepo.findByAuthor(author);
		return Optional.ofNullable(books);
	}

}
