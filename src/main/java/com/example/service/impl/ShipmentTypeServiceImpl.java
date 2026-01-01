package com.example.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.ShipmentTypeNotFoundException;
import com.example.model.ShipmentType;
import com.example.repo.ShipmentRepository;
import com.example.service.IShipmentTypeService;

@Service
public class ShipmentTypeServiceImpl implements IShipmentTypeService {

	@Autowired
	private ShipmentRepository shipRepo;
	
	@Override
	public Integer createShipmentType(ShipmentType shipmentType) {
		if(shipRepo.existsByShipCode(shipmentType.getShipCode()))
			throw new RuntimeException("Shipment code already exists");
		return shipRepo.save(shipmentType).getShipId();
	}

	@Override
	public ShipmentType getOneShipmentType(Integer id) {
		return shipRepo.findById(id).orElseThrow(()->new ShipmentTypeNotFoundException("Shipment not found with id: "+id));
	}

	@Override
	public List<ShipmentType> getAllShipmentType() {
		return shipRepo.findAll();
	}

	@Override
	public void updateShipmentType(ShipmentType shipmentType) {
		getOneShipmentType(shipmentType.getShipId());
		shipRepo.save(shipmentType);
	}

	@Override
	public void deleteShipmentType(Integer id) {
		getOneShipmentType(id);
		shipRepo.deleteById(id);
	}

	@Override
	public Map<Integer, String> getAllShipIdAndShipCode(String isShipmentEnabled) {
		return shipRepo.findAllShipIdAndShipCode(isShipmentEnabled).stream().collect(Collectors.toMap(
				ob->Integer.valueOf(ob[0].toString()), ob->String.valueOf(ob[1])));
	}

}
