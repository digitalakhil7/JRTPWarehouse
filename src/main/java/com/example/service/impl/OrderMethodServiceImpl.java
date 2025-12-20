package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.exception.OrderNotFoundException;
import com.example.model.OrderMethod;
import com.example.repo.OrderMethodRepository;
import com.example.service.IOrderMethodService;

public class OrderMethodServiceImpl implements IOrderMethodService {
	
	@Autowired
	private OrderMethodRepository orderRepo;

	@Override
	public Integer createOrderMethod(OrderMethod orderMethod) {
		return orderRepo.save(orderMethod).getOrderId();
	}

	@Override
	public OrderMethod getOneOrderMethod(Integer id) {
		return orderRepo.findById(id).orElseThrow(()->new OrderNotFoundException("Order not found with id: "+id));
	}

	@Override
	public List<OrderMethod> getAllOrderMethod() {
		return orderRepo.findAll();
	}

	@Override
	public void updateOrderMethod(OrderMethod orderMethod) {
		getOneOrderMethod(orderMethod.getOrderId());
		orderRepo.save(orderMethod);
	}

	@Override
	public void deleteOrderMethod(Integer id) {
		getOneOrderMethod(id);
		orderRepo.deleteById(id);
	}

}
