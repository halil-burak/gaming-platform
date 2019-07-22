package com.turkcell.playcell.gamingplatform.common.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.turkcell.playcell.gamingplatform.common.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
    List<Category> findDistinctByPlatformsNameEqualsIgnoreCaseAndIsTagFalse(String platformName);

    List<Category> findDistinctByPlatformsNameEqualsIgnoreCaseAndIsTagTrue(String platformName);

    List<Category> findDistinctByPlatformsId(Long platformId);

    @Query("SELECT c.id FROM Category c WHERE (SELECT p FROM Platform p where p.id = :platformId) MEMBER OF c.platforms")
    List<Long> findIDsByPlatformsId(@Param("platformId") Long platformId);
}
