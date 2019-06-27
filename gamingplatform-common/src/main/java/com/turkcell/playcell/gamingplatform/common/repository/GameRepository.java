package com.turkcell.playcell.gamingplatform.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.turkcell.playcell.gamingplatform.common.entity.Game;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findDistinctByGameDetailsPlatformNameEqualsIgnoreCaseAndGameDetailsCategoriesId(String platformName, Long categoryId);

    @Query(value = SqlQueries.FIND_GAME_URL)
    Game findGameUrl(String platformName, String languageName, String url);

}


