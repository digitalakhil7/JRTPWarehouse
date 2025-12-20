package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="uom_table")
public class Uom {
	@Id
	@Column(name="uom_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uomId;
	
	@Column(name="uom_type")
	private String uomType;
	
	@Column(name="uom_model")
	private String uomModel;
	
	@Column(name="uom_desc")
	private String uomDescription;
	
}
