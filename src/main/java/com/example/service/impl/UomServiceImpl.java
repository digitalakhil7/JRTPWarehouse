package com.example.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.UomNotFoundException;
import com.example.exception.UomNullPointerException;
import com.example.model.Uom;
import com.example.repo.UomRepository;
import com.example.service.IUomService;

@Service
public class UomServiceImpl implements IUomService {

	@Autowired
	private UomRepository uomRepo;
	
	@Override
	public Integer createUom(Uom uom) {
		return uomRepo.save(uom).getUomId();
	}

	@Override
	public Uom getOneUom(Integer id) {
		if(id==null) {
			throw new UomNullPointerException("uomId cannot be null");
		}
		return uomRepo.findById(id).orElseThrow(()->new UomNotFoundException("Uom not found with id: "+id));
	}

	@Override
	public List<Uom> getAllUom() {
		return uomRepo.findAll().stream().sorted(Comparator.comparing(Uom::getUomId).reversed()).collect(Collectors.toList());
	}

	@Override
	public void updateUom(Uom uom) {
		getOneUom(uom.getUomId());
		uomRepo.save(uom);
	}

	@Override
	public void deleteUom(Integer id) {
		getOneUom(id);
		uomRepo.deleteById(id);
	}

}
