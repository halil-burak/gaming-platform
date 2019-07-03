package com.turkcell.playcell.gamingplatform.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.playcell.gamingplatform.common.entity.GameDetailTranslation;


@Repository
public interface GameDetailTranslationRepository extends JpaRepository<GameDetailTranslation, Long> {
	
    GameDetailTranslation findByLanguageShortNameEqualsIgnoreCaseAndGameDetailId(String language, Long gameDetailId);
}
