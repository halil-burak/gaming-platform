package com.turkcell.playcell.gamingplatform.cms.service;

import com.turkcell.playcell.gamingplatform.cms.dto.category.CategoryCreateDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.category.CategoryDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.category.CategoryGetDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.category.CategoryLightDTO;
import com.turkcell.playcell.gamingplatform.cms.exception.FileDeleteException;

import java.util.List;

public interface CategoryService {

    Long saveCategory(CategoryCreateDTO categoryCreateDTO);

    void updateCategory(Long id, CategoryCreateDTO categoryCreateDTO);

    void deleteCategory(Long id);

    List<CategoryGetDTO> getCategories();

    CategoryDTO getCategory(Long id);

    List<CategoryLightDTO> getCategoriesByPlatform(Long platformId);

    void updateGamesOfCategory(Long platformId, Long categoryId, List<Long> games);

    void deleteIconFromCategory(Long id, Long iconId) throws FileDeleteException;
}

