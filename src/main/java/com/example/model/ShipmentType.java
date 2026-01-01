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
@Table(name="shipmenttype_table")
public class ShipmentType {
	@Id
	@Column(name="ship_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer shipId;
	
	@Column(name="ship_mode")
	private String shipMode;
	
	@Column(name="ship_code", unique = true)
	private String shipCode;
	
	@Column(name="ship_isenabled")
	private String isShipmentEnabled;
	
	@Column(name="ship_grade")
	private String shipGrade;
	
	@Column(name="ship_desc")
	private String shipDesc;
	
}
