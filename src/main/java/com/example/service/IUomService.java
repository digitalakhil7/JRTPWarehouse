package com.example.service;

import java.util.List;
import java.util.Map;

import com.example.model.Uom;

public interface IUomService {
	//create
	public Integer createUom(Uom uom);
	
	//read
	public Uom getOneUom(Integer id);
	public List<Uom> getAllUom();
	public Map<Integer, String> getUomIdAndModel();
	
	//update
	public void updateUom(Uom uom);
	
	//delete
	public void deleteUom(Integer id);
	
	public byte[] excelExport();
}
