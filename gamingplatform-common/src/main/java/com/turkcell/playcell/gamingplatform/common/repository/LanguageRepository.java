package com.turkcell.playcell.gamingplatform.common.repository;

import com.turkcell.playcell.gamingplatform.common.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

    List<Language> findDistinctByPlatformsId(Long platformId);

    @Query("SELECT l.id FROM Language l WHERE (SELECT p FROM Platform p where p.id = :platformId) MEMBER OF l.platforms")
    List<Long> findIDsByPlatformsId(@Param("platformId") Long platformId);

    Language findByShortName(String shortName);
}
