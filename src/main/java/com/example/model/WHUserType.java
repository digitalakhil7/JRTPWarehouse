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
@Table(name="whusertype_table")
public class WHUserType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="wh_id")
	private Integer whId;
	
	@Column(name="user_type")
	private String whUserType;
	
	@Column(name="user_code")
	private String whUserCode;
	
	@Column(name="email")
	private String whUserEmail;
	
	@Column(name="contact")
	private String whUserContact;
	
	@Column(name="userid_type")
	private String whUserIdType;

	@Column(name="id_number")
	private String whIdNumber;
}
