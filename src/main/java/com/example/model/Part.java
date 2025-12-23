package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="part_table")
public class Part {
	@Id
	@Column(name="part_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer partId;
	
	@Column(name="part_code")
	private String partCode;
	
	@Column(name="part_length")
	private Integer partLength;
	
	@Column(name="part_width")
	private Integer partWidth;
	
	@Column(name="part_height")
	private Integer partHeight;
	
	@Column(name="part_base_cost")
	private Integer partBaseCost;
	
	@Column(name="part_base_currency")
	private String partBaseCurrency;
	
	@ManyToOne
	@JoinColumn(name="uom_id_fk")
	private Uom uom;
	
}
