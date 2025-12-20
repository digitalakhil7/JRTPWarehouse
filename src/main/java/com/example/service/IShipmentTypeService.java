package com.example.service;

import java.util.List;

import com.example.model.ShipmentType;

public interface IShipmentTypeService {
	//create
	public Integer createShipmentType(ShipmentType ShipmentType);
	
	//read
	public ShipmentType getOneShipmentType(Integer id);
	public List<ShipmentType> getAllShipmentType();
	
	//update
	public void updateShipmentType(ShipmentType ShipmentType);
	
	//delete
	public void deleteShipmentType(Integer id);
}
