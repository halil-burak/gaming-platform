package com.turkcell.playcell.gamingplatform.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.turkcell.playcell.gamingplatform.common.entity.Game;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findDistinctByGameDetailsPlatformNameEqualsIgnoreCaseAndGameDetailsCategoriesId(String platformName, Long categoryId);

    //todo: tek bir tane oyun dönmeli. Unique constrain sebebi ile şimdilik böyle
    @Query(value = SqlQueries.FIND_GAME_URL)
    List<Game> findGameUrl(String platformName, String languageName, String url);

}


