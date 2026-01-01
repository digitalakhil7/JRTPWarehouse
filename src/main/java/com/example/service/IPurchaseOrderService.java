package com.example.service;

import java.util.List;

import com.example.model.PurchaseOrder;

public interface IPurchaseOrderService {
	//create
	public Integer createPurchaseOrder(PurchaseOrder purchaseOrder);
	
	//read
	public PurchaseOrder getOnePurchaseOrder(Integer id);
	public List<PurchaseOrder> getAllPurchaseOrder();
	
	//update
	public void updatePurchaseOrder(PurchaseOrder purchaseOrder);
	
	//delete
	public void deletePurchaseOrder(Integer id);
}
