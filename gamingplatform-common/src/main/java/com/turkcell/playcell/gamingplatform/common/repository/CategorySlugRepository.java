package com.turkcell.playcell.gamingplatform.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.playcell.gamingplatform.common.entity.CategorySlug;


@Repository
public interface CategorySlugRepository extends JpaRepository<CategorySlug, Long> {
	
    CategorySlug findFirst1ByCategoryTranslation_IdOrderByCreatedDateDesc(Long categoryTranslationId);
}
