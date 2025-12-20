package com.example.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.StudentNotFoundException;
import com.example.model.Student;
import com.example.repo.StudentRepository;

@RestController
public class StudentRestController {
	
	@Autowired
	private StudentRepository repo;
	
	@PostMapping("/create")
	public ResponseEntity<String> saveStudent(@RequestBody Student s){
		return new ResponseEntity<>("Student saved: "+repo.save(s).getSid(), HttpStatus.CREATED);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Student>> getStudent(){
		List<Student> allStudents = repo.findAll().stream().sorted(Comparator.comparing(Student::getSid).reversed()).collect(Collectors.toList());
		return new ResponseEntity<>(allStudents, HttpStatus.OK);
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Student> getOneStudent(@PathVariable Integer id){
		Student student = repo.findById(id).orElseThrow(()->new StudentNotFoundException("Student not found with id: "+id));
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateStudent(@RequestBody Student s){
		repo.findById(s.getSid()).orElseThrow(()->new StudentNotFoundException("Student not found with id: "+s.getSid()));
		repo.save(s);
		return new ResponseEntity<>("Student updated with id: "+s.getSid(), HttpStatus.OK);
	}
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<String> deleteOneStudent(@PathVariable Integer id){		
		repo.findById(id).orElseThrow(()->new StudentNotFoundException("Student not found with id: "+id));
		return new ResponseEntity<>("Student deleted with id: "+id, HttpStatus.OK);
	}
	
}
