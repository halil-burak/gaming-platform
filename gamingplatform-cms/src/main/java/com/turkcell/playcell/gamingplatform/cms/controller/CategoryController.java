package com.turkcell.playcell.gamingplatform.cms.controller;

import com.turkcell.playcell.gamingplatform.cms.dto.category.CategoryCreateDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.category.CategoryDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.category.CategoryGetDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.category.CategoryLightDTO;
import com.turkcell.playcell.gamingplatform.cms.exception.FileDeleteException;
import com.turkcell.playcell.gamingplatform.cms.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("")
    public Long saveCategory(@RequestBody @Valid CategoryCreateDTO categoryCreateDTO) {
        return categoryService.saveCategory(categoryCreateDTO);
    }

    @PutMapping("/{id}")
    public void updateCategory(@PathVariable(name = "id")Long id, @RequestBody @Valid CategoryCreateDTO categoryCreateDTO) {
        categoryService.updateCategory(id, categoryCreateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable(name = "id") Long id) {
        categoryService.deleteCategory(id);
    }

    @GetMapping("")
    public List<CategoryGetDTO> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    public CategoryDTO getCategory(@PathVariable(name = "id") Long id) {
        return categoryService.getCategory(id);
    }

    @GetMapping("/platforms/{platformId}")
    public List<CategoryLightDTO> getCategoriesByPlatform(@PathVariable(name = "platformId") Long platformId) {
        return categoryService.getCategoriesByPlatform(platformId);
    }

    @PutMapping("/{categoryId}/platforms/{platformId}/games")
    public void updateGamesOfCategory(@PathVariable(name = "categoryId") Long categoryId, @PathVariable(name = "platformId") Long platformId, @RequestBody List<Long> games) {
        categoryService.updateGamesOfCategory(platformId, categoryId, games);
    }

    @DeleteMapping("/{id}/icons/{iconId}")
    public void deleteIconFromCategory(@PathVariable(name = "id") Long id, @PathVariable(name = "iconId") Long iconId) throws FileDeleteException {
        categoryService.deleteIconFromCategory(id, iconId);
    }
}
