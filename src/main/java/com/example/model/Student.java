package com.example.model;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "student_table")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private Integer sid;
	
	@Column(name = "name")
	private String sname;
	
	@Column(name = "fee")
	private Double sfee;
	
	@ElementCollection
	@CollectionTable(name="subject_table", joinColumns = @JoinColumn(name="student_id"))
	@Column(name="subject_name")
	private List<String> subjects;
}
