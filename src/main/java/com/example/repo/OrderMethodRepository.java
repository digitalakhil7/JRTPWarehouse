package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.OrderMethod;
import com.example.model.Uom;

@Repository
public interface OrderMethodRepository extends JpaRepository<OrderMethod, Integer> {

}
