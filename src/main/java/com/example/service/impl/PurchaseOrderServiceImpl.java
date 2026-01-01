package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.PurchaseOrder;
import com.example.repo.PurchaseOrderRepository;
import com.example.service.IPurchaseOrderService;

@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository poRepo;
	
	@Override
	public Integer createPurchaseOrder(PurchaseOrder purchaseOrder) {
		if(poRepo.existsByPoCode(purchaseOrder.getPoCode()))
			throw new RuntimeException("Purchase Order code already exists");
		return poRepo.save(purchaseOrder).getPoId();
	}

	@Override
	public PurchaseOrder getOnePurchaseOrder(Integer id) {
		if(id==null)
			throw new RuntimeException("Purchase Order id cannot be null");
		return poRepo.findById(id).orElseThrow(()-> new RuntimeException("Purchase Order not found with id: "+id));
	}

	@Override
	public List<PurchaseOrder> getAllPurchaseOrder() {
		return poRepo.findAll();
	}

	@Override
	public void updatePurchaseOrder(PurchaseOrder purchaseOrder) {
		getOnePurchaseOrder(purchaseOrder.getPoId());
		poRepo.save(purchaseOrder);
	}

	@Override
	public void deletePurchaseOrder(Integer id) {
		getOnePurchaseOrder(id);
		poRepo.deleteById(id);
	}

}
