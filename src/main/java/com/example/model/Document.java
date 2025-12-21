package com.example.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "document_table")
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="document_id")
	private Integer docId;
	
	@Column(name="document_name")
	private String docName;
	
	@Lob
	@Column(name="document_data", columnDefinition = "LONGBLOB")
	private byte[] docData;
	
	@Column(name="uploaded_by")
	private String docUploadedBy;
	
}
