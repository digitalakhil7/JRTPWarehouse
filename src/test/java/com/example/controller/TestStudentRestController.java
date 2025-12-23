package com.example.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import tools.jackson.core.JacksonException;

@SpringBootTest
@AutoConfigureMockMvc
public class TestStudentRestController {
	
	// make http calls
	@Autowired
	private MockMvc mockMvc;
	
	String json = """
			{
				"sid": 1,
			    "sname": "Akhil",
			    "sfee": 60.0,
			    "subjects": ["Java", "Vegan"]
			}
			""";
	
	@Test
	public void testSaveStudent() throws JacksonException, Exception {
		mockMvc.perform(post("/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		        .andExpect(status().isCreated())
		        .andExpect(content().string("Student saved: 1"));
				
	}
	
	@Test
	public void testGetAllStudents() throws JacksonException, Exception {
		mockMvc.perform(get("/findAll")
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$").isArray());
 	}
	
//	@Test
//	public void testGetOneStudent() throws Exception{
//		mockMvc.perform(get("/findById/1")
//			   .contentType(MediaType.APPLICATION_JSON))
//			   .andExpect(status().isOk())
//			   .andExpect(content().json(json));
//	}
}