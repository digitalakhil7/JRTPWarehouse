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

import com.example.model.Uom;
import com.example.service.IUomService;

@RestController
@RequestMapping("/uom")
public class UomController {
	
	@Autowired
	private IUomService uomService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createUom(@RequestBody Uom uom){
		return new ResponseEntity<>("Uom created with id: "+uomService.createUom(uom), HttpStatus.CREATED);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Uom>> getAllUom(){
		return new ResponseEntity<>(uomService.getAllUom(), HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateUom(@RequestBody Uom uom){
		uomService.updateUom(uom);
		return new ResponseEntity<>("Uom updated with id: "+uom.getUomId(), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUom(@PathVariable Integer id){
		uomService.deleteUom(id);
		return new ResponseEntity<>("Uom deleted for id: "+id, HttpStatus.OK);
	}
}

