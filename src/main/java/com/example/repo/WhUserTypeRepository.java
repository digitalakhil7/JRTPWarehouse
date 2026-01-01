package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.WhUserType;

@Repository
public interface WhUserTypeRepository extends JpaRepository<WhUserType, Integer> {
	public boolean existsByWhUserEmail(String whUserEmail);
	
	@Query("select whId, whUserCode from WhUserType wh where whUserType=:whUserType")
	public List<Object[]> findAllWhIdAndWhUserCode(String whUserType);
}
