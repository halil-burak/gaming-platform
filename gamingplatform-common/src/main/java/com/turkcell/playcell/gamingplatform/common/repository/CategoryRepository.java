package com.turkcell.playcell.gamingplatform.common.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.playcell.gamingplatform.common.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
    List<Category> findDistinctByPlatformsNameEqualsIgnoreCaseAndIsTagFalse(String platformName);

    List<Category> findDistinctByPlatformsNameEqualsIgnoreCaseAndIsTagTrue(String platformName);
}
