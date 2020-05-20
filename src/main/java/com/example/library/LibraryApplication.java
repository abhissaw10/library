package com.example.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.example.library.repository.BookRepository;

@SpringBootApplication
public class LibraryApplication {

	@Autowired
	BookRepository bookRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void startApp() {
		bookRepo.deleteAll();
	}

}
