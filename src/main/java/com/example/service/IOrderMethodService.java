package com.example.service;

import java.util.List;
import java.util.Map;

import com.example.model.OrderMethod;

public interface IOrderMethodService {
	// create
	public Integer createOrderMethod(OrderMethod orderMethod);

	// read
	public OrderMethod getOneOrderMethod(Integer id);
	public List<OrderMethod> getAllOrderMethod();
	public Map<Integer,String> findAllOrderIdAndCodeByOrderMode(String orderMode);

	// update
	public void updateOrderMethod(OrderMethod orderMethod);

	// delete
	public void deleteOrderMethod(Integer id);

	// exports
	public byte[] excelExport();

	public byte[] pdfExport();

	// validation
	public boolean isOrderCodeExist(String orderCode);
}
