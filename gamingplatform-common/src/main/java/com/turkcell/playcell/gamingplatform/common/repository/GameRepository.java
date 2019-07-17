package com.turkcell.playcell.gamingplatform.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.turkcell.playcell.gamingplatform.common.entity.Game;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findDistinctByGameDetailsPlatformNameEqualsIgnoreCaseAndGameDetailsCategoriesId(String platformName, Long categoryId);

    @Query(value = SqlQueries.FIND_GAME_URL)
    Game findGameUrl(String platformName, String languageName, String url);

    List<Game> findDistinctByGameDetailsPlatformId(Long platformId);

    List<Game> findDistinctByGameDetailsPlatformIdAndGameDetailsCategoriesId(Long platformId, Long categoryId);

    @Query("SELECT DISTINCT g FROM Game g LEFT JOIN g.gameDetails gd " +
            "WHERE (:search IS NULL OR :search = '' OR lower(g.name) like lower(concat('%', :search,'%'))) " +
            "AND ((:onlyPublished = false AND (:platforms IS NULL OR gd.platform.id IN (:platforms))) OR (:onlyPublished = true AND gd.isActive = true AND CURRENT_TIMESTAMP > gd.publishDatetime AND (gd.unpublishDatetime IS NULL OR CURRENT_TIMESTAMP < gd.unpublishDatetime) AND (:platforms IS NULL OR gd.platform.id IN (:platforms))))")
    Page<Game> findGameByFilter(@Param("platforms") List<Long> platforms, @Param("search") String search, @Param("onlyPublished") boolean onlyPublished, Pageable pageable);
}


