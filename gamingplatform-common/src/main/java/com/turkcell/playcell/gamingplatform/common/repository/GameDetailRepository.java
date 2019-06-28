package com.turkcell.playcell.gamingplatform.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.turkcell.playcell.gamingplatform.common.entity.Game;
import com.turkcell.playcell.gamingplatform.common.entity.GameDetail;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface GameDetailRepository extends JpaRepository<GameDetail, Long> {
	
    List<GameDetail> findByPlatformId(Long platformId);

    List<GameDetail> findByPlatformNameEqualsIgnoreCaseAndGameIdAndIsActiveTrue(String platformName, Long gameId);

    Optional<GameDetail> findByPlatformNameEqualsIgnoreCaseAndGameId(String platformName, Long gameId);

    @Query(value = SqlQueries.FIND_GAME_BY_PLATFORM_NAME_AND_LANGUAGE)
    List<Game> findGameByPlatformName(@Param("platformName") String platformName, @Param("language") String language, @Param("datetime")Instant datetime);

    @Query(value = SqlQueries.FIND_TOTAL_GAME_NUMBER, nativeQuery = true)
    Long findTotalGameNumber();

    @Query(value = SqlQueries.FIND_GAME_NUMBER_BY_TARIFF, nativeQuery = true)
    Long findTotalGameNumberByTariff(String tariffName, String platformName);

    @Query(value = SqlQueries.FIND_TOTAL_CATEGORY_NUMBER, nativeQuery = true)
    Long findTotalCategoryNumber();

    @Query(value = SqlQueries.FIND_GAME_BY_PLATFORM_ID_AND_TARIFF_ID)
    List<Game> findGameByPlatformIdAndTariffId(@Param("platformId") Long platformId, @Param("tariffId") Long tariffId);
    
}
