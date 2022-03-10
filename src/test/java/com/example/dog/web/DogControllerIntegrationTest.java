package com.example.dog.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.dog.domain.Dog;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts= {"classpath:dog-schema.sql", "classpath:dog-data.sql"}, executionPhase=ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class DogControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void testCreate() throws Exception {
		Dog testDog = new Dog(null, "Golden Retriever", "Lox", 1, false);
		String testDogAsJSON = this.mapper.writeValueAsString(testDog);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testDogAsJSON);
		
		Dog testCreatedDog = new Dog(3, "Golden Retriever", "Lox", 1, false);
		String testCreatedDogAsJSON = this.mapper.writeValueAsString(testCreatedDog);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedDogAsJSON);
		
		this.mvc.perform(req).andExpectAll(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testUpdate() throws Exception {
		Dog testDog = new Dog(null, "Japenese Akita", "Hound", 5, false);
		String testDogAsJSON = this.mapper.writeValueAsString(testDog);
		RequestBuilder req = put("/replace/1").contentType(MediaType.APPLICATION_JSON).content(testDogAsJSON);
		
		Dog testUpdatedDog = new Dog(1, "Japenese Akita", "Hound", 5, false);
		String testUpdatedDogAsJSON = this.mapper.writeValueAsString(testUpdatedDog);
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testUpdatedDogAsJSON);
		
		this.mvc.perform(req).andExpectAll(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testGetAll() throws Exception {
		RequestBuilder req = get("/getAll");
		List<Dog> testDogs = List.of(new Dog(1, "Husky", "Snow", 4, true), new Dog(2, "Labrador", "Andrex", 8, false));
		String json = this.mapper.writeValueAsString(testDogs);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpectAll(checkStatus).andExpectAll(checkBody);
	}
	
	@Test
	void testGetById() throws Exception {
		RequestBuilder req = get("/get/1");
		Dog testDogById = new Dog(1, "Husky", "Snow", 4, true);
		String json = this.mapper.writeValueAsString(testDogById);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpectAll(checkStatus).andExpectAll(checkBody);
	}
	
	@Test
	void testDelete() throws Exception {
		RequestBuilder req = delete("/remove/1");
		ResultMatcher checkStatus = status().isNoContent();
		
		this.mvc.perform(req).andExpectAll(checkStatus);
	}
}
