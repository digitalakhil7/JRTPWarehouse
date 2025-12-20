package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.ShipmentType;
import com.example.service.IShipmentTypeService;

@RestController
@RequestMapping("/ship")
public class ShipmentController {
	
	@Autowired
	private IShipmentTypeService shipService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createShipment(@RequestBody ShipmentType shipmentType){
		return new ResponseEntity<>("Shipment created with id: "+shipService.createShipmentType(shipmentType), HttpStatus.CREATED);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<ShipmentType>> getAllShipments(){
		return new ResponseEntity<>(shipService.getAllShipmentType(), HttpStatus.OK);
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<ShipmentType> getOneShipment(@PathVariable Integer id){
		return new ResponseEntity<>(shipService.getOneShipmentType(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteOneShipment(@PathVariable Integer id){
		shipService.deleteShipmentType(id);
		return new ResponseEntity<>("Shipment deleted for id: "+id, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateShipment(@RequestBody ShipmentType shipmentType){
		shipService.updateShipmentType(shipmentType);
		return new ResponseEntity<>("Shipment updated with id: "+shipmentType.getShipId(), HttpStatus.CREATED);
	}
}

