package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.ShipmentType;
import com.example.model.Uom;

@Repository
public interface ShipmentRepository extends JpaRepository<ShipmentType, Integer> {

}
