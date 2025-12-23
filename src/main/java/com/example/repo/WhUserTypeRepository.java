package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.WhUserType;

@Repository
public interface WhUserTypeRepository extends JpaRepository<WhUserType, Integer> {
	public boolean existsByWhUserEmail(String whUserEmail);
}
