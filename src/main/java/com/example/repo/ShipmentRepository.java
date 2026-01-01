package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.ShipmentType;

@Repository
public interface ShipmentRepository extends JpaRepository<ShipmentType, Integer> {
	public boolean existsByShipCode(String shipCode);
	
	@Query("select shipId, shipCode from ShipmentType s where isShipmentEnabled=:isShipmentEnabled")
	public List<Object[]> findAllShipIdAndShipCode(String isShipmentEnabled);
	
}
