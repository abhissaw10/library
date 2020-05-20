package com.example.library.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookControllerTest {

	@Mock
	BookService service;
	
	@Autowired
	private MockMvc mockMvc;
	
	Book b;
	
	@Before
	public void setup() throws Exception {
		b = new Book();
		b.setAuthor("ChrisR");
		b.setBookName("MsP");
	}
	
	@Test
	public void whenCreateThenReturnsBook() throws Exception {
		Mockito.when(service.create(b)).thenReturn(b);
		mockMvc.perform(post("/book/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(convertObjectToJsonBytes(b)))
		.andExpect(status().is2xxSuccessful())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("@.author").value("ChrisR"))
		.andReturn();
	}
	
	public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
	
	@Test
	public void whenGetByAuthorNameReturnsBookList() throws Exception {
		List<Book> books = new ArrayList<>();
		books.add(b);
		Mockito.when(service.findByAuthor(Mockito.anyString())).thenReturn(Optional.ofNullable(books));
		mockMvc.perform(get("/book/byAuthor/ChrisR"))
		.andExpect(status().is2xxSuccessful())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("@.[0].author").value("ChrisR"))
		.andReturn();
	}
}
