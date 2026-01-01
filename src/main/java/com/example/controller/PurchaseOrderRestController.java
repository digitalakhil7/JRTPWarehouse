package com.example.controller;

import java.util.List;
import java.util.Map;

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

import com.example.model.PurchaseOrder;
import com.example.service.IPurchaseOrderService;
import com.example.service.IShipmentTypeService;
import com.example.service.IWhUserTypeService;

@RestController
@RequestMapping("/po")
public class PurchaseOrderRestController {
	
	@Autowired
	private IPurchaseOrderService poService;
	
	@Autowired
	private IShipmentTypeService shipService;
	
	@Autowired
	private IWhUserTypeService whService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createPo(@RequestBody PurchaseOrder po){
		return new ResponseEntity<String>("Purchase Order created with id: "+poService.createPurchaseOrder(po), HttpStatus.CREATED);
	}
	
	@GetMapping("/findOne/{id}")
	public ResponseEntity<PurchaseOrder> getOnePo(@PathVariable Integer id){
		return new ResponseEntity<PurchaseOrder>(poService.getOnePurchaseOrder(id), HttpStatus.OK);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<PurchaseOrder>> getAllPo(){
		return new ResponseEntity<java.util.List<PurchaseOrder>>(poService.getAllPurchaseOrder(), HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updatePo(@RequestBody PurchaseOrder po){
		poService.updatePurchaseOrder(po);
		return new ResponseEntity<>("Purchase Order created with id: "+po.getPoId(), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePo(@PathVariable Integer id){
		poService.deletePurchaseOrder(id);
		return new ResponseEntity<String>("Purchase Order deleted with id: "+id, HttpStatus.OK);
	}
	
	@GetMapping("/findAllEnabledShip")
	public ResponseEntity<Map<Integer, String>> getAllShipIdAndShipCode(){
		return new ResponseEntity<>(shipService.getAllShipIdAndShipCode("YES"), HttpStatus.OK);
	}
	
	@GetMapping("/findAllVendorWh")
	public ResponseEntity<Map<Integer, String>> getAllWhIdAndWhUserCode(){
		return new ResponseEntity<>(whService.getAllWhIdAndWhUserCode("Vendor"), HttpStatus.OK);
	}
}
