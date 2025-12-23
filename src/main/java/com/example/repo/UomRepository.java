package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.Uom;

@Repository
public interface UomRepository extends JpaRepository<Uom, Integer> {
	public boolean existsByUomModel(String uomModel);
	
	@Query("select uomId, uomModel from Uom")
	public List<Object[]> findAllUomIdAndModel();
}
