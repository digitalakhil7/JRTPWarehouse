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
@Table(name="whusertype_table")
public class WhUserType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="wh_id")
	private Integer whId;
	
	@Column(name="user_type")
	private String whUserType;
	
	@Column(name="user_code", unique=true)
	private String whUserCode;
	
	@Column(name="user_for")
	private String whUserFor;
	
	@Column(name="email", unique=true)
	private String whUserEmail;
	
	@Column(name="contact")
	private String whUserContact;
	
	@Column(name="userid_type")
	private String whUserIdType;

	@Column(name="id_number")
	private String whIdNumber;
}
