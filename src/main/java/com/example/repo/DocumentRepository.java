package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
	
	@Query("select docId, docName from Document d")
	List<Object[]> getDocumentIdAndName();
}
