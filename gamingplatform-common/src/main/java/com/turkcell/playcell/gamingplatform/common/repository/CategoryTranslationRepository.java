package com.turkcell.playcell.gamingplatform.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.playcell.gamingplatform.common.entity.CategoryTranslation;


@Repository
public interface CategoryTranslationRepository extends JpaRepository<CategoryTranslation, Long> {
    CategoryTranslation findByLanguageShortNameEqualsIgnoreCaseAndCategoryId(String language, Long categoryId);
}
