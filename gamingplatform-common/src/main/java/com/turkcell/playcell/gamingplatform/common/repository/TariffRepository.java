package com.turkcell.playcell.gamingplatform.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.playcell.gamingplatform.common.entity.Tariff;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Long> {
    Tariff findByName(String name);
}
