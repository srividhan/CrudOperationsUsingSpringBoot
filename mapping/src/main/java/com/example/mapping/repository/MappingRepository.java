package com.example.mapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mapping.model.Mapping;

public interface MappingRepository extends JpaRepository<Mapping, Long>{
    
}
