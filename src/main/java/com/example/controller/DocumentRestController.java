package com.example.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Document;
import com.example.repo.DocumentRepository;

@RestController
@RequestMapping("/document")
public class DocumentRestController {
	
	@Autowired
	private DocumentRepository docRepo;
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadDocument(@RequestParam MultipartFile file,
			@RequestParam String uploadedBy) throws IOException{
		
		if(file.isEmpty()) {
			return new ResponseEntity<>("File is missing or empty", HttpStatus.BAD_REQUEST); 
		}
		
		Document document = new Document();
		document.setDocName(file.getOriginalFilename());
		document.setDocData(file.getBytes());
		document.setDocUploadedBy(uploadedBy);

		return new ResponseEntity<>("Document uploaded with id: "+docRepo.save(document).getDocId(), HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<Object[]>> getdocIdAnddocName(){
		return new ResponseEntity<>(docRepo.getDocumentIdAndName(), HttpStatus.OK);
	}
	
	@GetMapping("/download/{id}")
	public ResponseEntity<byte[]> downloadDocument(@PathVariable Integer id){
		
		Document document = docRepo.findById(id).orElseThrow(()->new RuntimeException("Document not found with id: "+id));
		
		// set doc name and download
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.attachment().filename(document.getDocName()).build());
		
		return new ResponseEntity<>(document.getDocData(), headers, HttpStatus.OK);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException e){
		
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
}
