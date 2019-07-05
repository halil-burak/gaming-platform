package com.turkcell.playcell.gamingplatform.common.repository;

import com.turkcell.playcell.gamingplatform.common.entity.PlatformTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformTranslationRepository extends JpaRepository<PlatformTranslation, Long> {
}
