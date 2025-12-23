package com.example.service;

import java.util.List;

import com.example.model.WhUserType;

public interface IWhUserTypeService {
	//create
	public Integer createWhUserType(WhUserType whUserType);
	
	//read
	public WhUserType getOneWhUserType(Integer id);
	public List<WhUserType> getAllWhUserType();
	
	//update
	public void updateWhUserType(WhUserType whUserType);
	
	//delete
	public void deleteWhUserType(Integer id);
	
	//exports
	public byte[] excelExport();
	
	public byte[] pdfExport();
	
	// validation
	public boolean isEmailExist(String whUserEmail);
}
