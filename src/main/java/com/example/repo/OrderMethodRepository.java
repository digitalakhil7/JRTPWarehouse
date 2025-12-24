package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.OrderMethod;

@Repository
public interface OrderMethodRepository extends JpaRepository<OrderMethod, Integer> {
    public boolean existsByOrderCode(String orderCode);
    
    @Query("select orderId, orderCode from OrderMethod o where o.orderMode=:orderMode")
    public List<Object[]> findAllOrderIdAndCodeByOrderMode(String orderMode);
}
