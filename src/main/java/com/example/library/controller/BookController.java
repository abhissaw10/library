package com.example.library.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.exception.BookNotFoundException;
import com.example.library.exception.ErrorCode;
import com.example.library.model.Book;
import com.example.library.service.BookService;

@RestController
@RequestMapping("/book/")
public class BookController {

	@Autowired
	BookService service;
	
	@PostMapping("create")
	public ResponseEntity<Book> create(@RequestBody Book b) throws Exception {
		return ResponseEntity.ok(service.create(b));
	}
	
	@GetMapping("byAuthor/{author}")
	public ResponseEntity<?> getByAuthor(@PathVariable("author") String author) throws Exception{
		//Optional<List<Book>> books = service.findByAuthor(author);
		return Optional.ofNullable(service.findByAuthor(author))
                .map(e -> new ResponseEntity<>(e, HttpStatus.CREATED))
                .orElseThrow(() -> new BookNotFoundException(ErrorCode.BOOK_NOT_FOUND));
		
	}
}
