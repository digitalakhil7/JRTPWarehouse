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
@Table(name="purchase_order")
public class PurchaseOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private Integer poId;
	
	@Column(name="order_code", unique=true)
	private String poCode;
	
	@ManyToOne
	@JoinColumn(name="ship_id_fk")
	private ShipmentType shipmentType;
	
	@ManyToOne
	@JoinColumn(name="wh_id_fk")
	private WhUserType whUserType;
	
	@Column(name="ref_num")
	private String poReferenceNo;
	
	@Column(name="quality_check")
	private String poQualityCheck;
	
	@Column(name="default_status")
	private String poDefaultStatus;
	
	@Column(name="description")
	private String poDescription;
}
