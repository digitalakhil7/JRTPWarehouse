package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Part;

@Repository
public interface PartRepository extends JpaRepository<Part, Integer> {
    public boolean existsByPartCode(String partCode);
}
