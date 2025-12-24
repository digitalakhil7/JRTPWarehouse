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
@Table(name="ordermethod_table")
public class OrderMethod {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private Integer orderId;
	
	@Column(name="order_mode")
	private String orderMode;
	
	@Column(name="order_code")
	private String orderCode;
	
	@Column(name="order_type")
	private String orderType;
	
	@ElementCollection
	@CollectionTable(name="orderaccept_table", joinColumns = @JoinColumn(name="order_id_fk"))
	@Column(name="order_accept")
	private List<String> orderAccept;
	
	@Column(name="order_desc")
	private String orderDesc;
	
}
