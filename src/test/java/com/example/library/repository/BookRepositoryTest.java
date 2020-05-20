package com.example.library.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;

@DataMongoTest
@RunWith(SpringRunner.class)
public class BookRepositoryTest {

	@Autowired
	BookRepository repo;
	
	Book b = null;
	
	@Before
	public void setUp() {
		b = new Book();
		b.setId(UUID.randomUUID().toString());
		b.setAuthor("Abhishek");
		repo.deleteAll();
	}
	
	@Test
	public void whenCreateReturnsBook() {
		Book res = repo.save(b);
		assertThat(res).isNotNull();
		assertThat(res.getId()).isNotBlank();
		assertThat("Abhishek").isEqualTo(res.getAuthor());
		count(1);
	}
	
	private void count(int count) {
		List<Book> bookList = repo.findAll();
		assertThat(bookList.size()).isEqualTo(count);
	}
	
	@Test
	public void whenfindAllReturnsCount() {
		count(0);
		repo.save(b);
		count(1);
	}
	
	@After
	public void tearDown() {
		repo.deleteAll();
	}
}
