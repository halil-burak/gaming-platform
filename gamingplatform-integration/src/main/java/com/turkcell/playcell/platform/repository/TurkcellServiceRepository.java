package com.turkcell.playcell.platform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.turkcell.playcell.platform.entity.TurkcellServiceConfig;

@Repository
public interface TurkcellServiceRepository extends JpaRepository<TurkcellServiceConfig, Long> {
	
	@Query("SELECT p FROM TurkcellServiceConfig p WHERE p.key_name = :KeyName")
    public Optional<TurkcellServiceConfig> findKey(@Param("KeyName") String name);

}
