package com.example.service;

import java.util.List;

import com.example.model.OrderMethod;

public interface IOrderMethodService {
	//create
	public Integer createOrderMethod(OrderMethod orderMethod);
	
	//read
	public OrderMethod getOneOrderMethod(Integer id);
	public List<OrderMethod> getAllOrderMethod();
	
	//update
	public void updateOrderMethod(OrderMethod orderMethod);
	
	//delete
	public void deleteOrderMethod(Integer id);
}
