package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

import com.example.model.WhUserType;
import com.example.service.IWhUserTypeService;
import com.example.util.EmailUtil;

@RestController
@RequestMapping("/wh")
public class WhUserTypeRestControlller {
	@Autowired
	private IWhUserTypeService whService;
	
	@Autowired
	private EmailUtil emailUtil;
	
	@PostMapping("/create")
	public ResponseEntity<String> createUom(@RequestBody WhUserType whUserType){
		Integer id = whService.createWhUserType(whUserType);
		if(id!=null) {
			new Thread(()->{
				emailUtil.sendEmail(whUserType.getWhUserEmail(), "Registration Success", "Login to access your account now");
			}).start();
		}
		return new ResponseEntity<>("WhUserType created with id: "+id, HttpStatus.CREATED);
	}
	
	@GetMapping("/findOne/{id}")
	public ResponseEntity<WhUserType> getOneWhUserType(@PathVariable Integer id){
		return new ResponseEntity<>(whService.getOneWhUserType(id), HttpStatus.OK);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<WhUserType>> getAllWhUserType(){
		return new ResponseEntity<>(whService.getAllWhUserType(), HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateUom(@RequestBody WhUserType whUserType){
		whService.updateWhUserType(whUserType);
		return new ResponseEntity<>("WhUserType updated with id: "+whUserType.getWhId(), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUom(@PathVariable Integer id){
		whService.deleteWhUserType(id);
		return new ResponseEntity<>("WhUserType deleted for id: "+id, HttpStatus.OK);
	}
	
	@GetMapping("/excel")
	public ResponseEntity<byte[]> exportExport(){
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Disposition", "attachment; filename=whusertypes.xlsx");
		
		return new ResponseEntity<>(whService.excelExport(), headers, HttpStatus.OK);
	}
	
	@GetMapping("/pdf")
	public ResponseEntity<byte[]> pdfExport(){
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Disposition", "attachment; filename=whusertypes.pdf");
		
		return new ResponseEntity<>(whService.pdfExport(), headers, HttpStatus.OK);
	}
	
	@GetMapping("/validate/{whUserEmail}")
	public ResponseEntity<String> validateEmail(@PathVariable String whUserEmail) {
		if(whService.isEmailExist(whUserEmail)) {
			return new ResponseEntity<>("Email: ALREADY USED", HttpStatus.OK);
		}
		return new ResponseEntity<>("Email: AVAILABLE", HttpStatus.OK);
	}
}
