package com.example.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.model.Student;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TestStudentRestController {
	
	// make http calls
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper om;
	
	@Test
	public void testSaveStudent() throws JacksonException, Exception {
		String json = """
				{
					"sid": 1,
				    "sname": "Akhil",
				    "sfee": 60.0,
				    "subjects": ["Java", "Vegan"]
				}
				""";
		
		mockMvc.perform(post("/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		        .andExpect(status().isCreated())
		        .andExpect(content().string("Student saved: 1"));
				
	}
	
	@Test
	public void testGetOneStudent() throws Exception{
		mockMvc.perform(get("/findById/1")
			   .contentType(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk());
	}
}