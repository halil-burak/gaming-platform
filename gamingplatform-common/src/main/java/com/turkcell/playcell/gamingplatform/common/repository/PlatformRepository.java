package com.turkcell.playcell.gamingplatform.common.repository;

import com.turkcell.playcell.gamingplatform.common.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long> {
    Platform findByNameIgnoreCase(String platformName);
}
