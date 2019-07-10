package com.turkcell.playcell.gamingplatform.cms.service.impl;

import com.turkcell.playcell.gamingplatform.cms.dto.ImageDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.category.*;
import com.turkcell.playcell.gamingplatform.cms.exception.FileDeleteException;
import com.turkcell.playcell.gamingplatform.cms.exception.NotFoundException;
import com.turkcell.playcell.gamingplatform.cms.service.CategoryService;
import com.turkcell.playcell.gamingplatform.cms.service.FileUploadService;
import com.turkcell.playcell.gamingplatform.common.entity.*;
import com.turkcell.playcell.gamingplatform.common.repository.CategoryRepository;
import com.turkcell.playcell.gamingplatform.common.repository.CategoryTranslationRepository;
import com.turkcell.playcell.gamingplatform.common.repository.GameDetailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    GameDetailRepository gameDetailRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryTranslationRepository categoryTranslationRepository;

    @Autowired
    FileUploadService fileUploadService;

    @Override
    public Long saveCategory(CategoryCreateDTO categoryCreateDTO) {
        Category category = new Category();
        category.setName(categoryCreateDTO.getName());
        category.setSeries(categoryCreateDTO.isSeries());
        category.setTag(categoryCreateDTO.isTag());
        category.setVisible(categoryCreateDTO.isVisible());
        category.setPlatforms(categoryCreateDTO.getPlatforms().stream().map(p -> new Platform(p)).collect(Collectors.toList()));
        //todo icon içinde platformlar dogrumu, translationda languages dogrumu
        category.getCategoryIcons().addAll(categoryCreateDTO.getIcons().stream().map(categoryIconDTO -> {
           CategoryIcon categoryIcon = new CategoryIcon();
           categoryIcon.setCategory(category);
           categoryIcon.setImage(new Image(categoryIconDTO.getImageId()));
           categoryIcon.setPlatform(new Platform(categoryIconDTO.getPlatformId()));
           return categoryIcon;
        }).collect(Collectors.toList()));
        category.getCategoryTranslations().addAll(categoryCreateDTO.getTranslations().stream().map(translationCreateDTO -> {
            CategoryTranslation categoryTranslation = new CategoryTranslation();
            categoryTranslation.setName(translationCreateDTO.getName());
            categoryTranslation.getCategorySlugs().addAll(translationCreateDTO.getSlugs().stream().map(slug -> new CategorySlug(slug)).collect(Collectors.toList()));
            categoryTranslation.setLanguage(new Language(translationCreateDTO.getLanguageId()));
            return categoryTranslation;

        }).collect(Collectors.toList()));
        return categoryRepository.save(category).getId();
    }

    @Override
    public void updateCategory(Long id, CategoryCreateDTO categoryCreateDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(Category.class, id));
        category.setName(categoryCreateDTO.getName());
        category.setSeries(categoryCreateDTO.isSeries());
        category.setTag(categoryCreateDTO.isTag());
        category.setVisible(categoryCreateDTO.isVisible());
        category.setPlatforms(categoryCreateDTO.getPlatforms().stream().map(p -> new Platform(p)).collect(Collectors.toList()));
        //todo icon içinde platformlar dogrumu, translationda languages dogrumu
        category.getCategoryIcons().clear();
        category.getCategoryIcons().addAll(categoryCreateDTO.getIcons().stream().map(categoryIconDTO -> {
            CategoryIcon categoryIcon = new CategoryIcon();
            categoryIcon.setId(categoryIconDTO.getId());
            categoryIcon.setCategory(category);
            categoryIcon.setImage(new Image(categoryIconDTO.getImageId()));
            categoryIcon.setPlatform(new Platform(categoryIconDTO.getPlatformId()));
            return categoryIcon;
        }).collect(Collectors.toList()));
        List<CategoryTranslation> categoryTranslations = categoryCreateDTO.getTranslations().stream().map(translationCreateDTO -> {
            CategoryTranslation categoryTranslation;
            if(translationCreateDTO.getId() == null || translationCreateDTO.getId().equals(0)){
                categoryTranslation = new CategoryTranslation();
                categoryTranslation.setCategory(category);
            }
            else{
                categoryTranslation = categoryTranslationRepository.findById(translationCreateDTO.getId()).get();
            }
            categoryTranslation.setName(translationCreateDTO.getName());

            categoryTranslation.getCategorySlugs().stream().filter(categorySlug -> ! translationCreateDTO.getSlugs().contains(categorySlug.getUrl())).collect(Collectors.toList())
                    .forEach(categorySlug -> categoryTranslation.removeCategorySlug(categorySlug));

            List<String> existingSlugs = categoryTranslation.getCategorySlugs().stream().map(categorySlug-> categorySlug.getUrl()).collect(Collectors.toList());
            translationCreateDTO.getSlugs().forEach(s -> {
                if (!existingSlugs.contains(s)) {
                    CategorySlug categorySlug = new CategorySlug();
                    categorySlug.setUrl(s);
                    categoryTranslation.addCategorySlug(categorySlug);
                }
            });

            categoryTranslation.setLanguage(new Language(translationCreateDTO.getLanguageId()));
            return categoryTranslation;

        }).collect(Collectors.toList());
        category.getCategoryTranslations().clear();
        category.getCategoryTranslations().addAll(categoryTranslations);
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryGetDTO> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(c -> {
            CategoryGetDTO categoryGetDTO = new CategoryGetDTO();
            categoryGetDTO.setId(c.getId());
            categoryGetDTO.setName(c.getName());
            categoryGetDTO.setSeries(c.isSeries());
            categoryGetDTO.setTag(c.isTag());
            categoryGetDTO.setVisible(c.isVisible());
            categoryGetDTO.setPlatforms(c.getPlatforms().stream().map(Platform::getId).collect(Collectors.toList()));
            return categoryGetDTO;

        }).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(Category.class, id));;
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setSeries(category.isSeries());
        categoryDTO.setTag(category.isTag());
        categoryDTO.setVisible(category.isVisible());
        categoryDTO.setIcons(category.getCategoryIcons().stream().map(categoryIcon -> {
            CategoryIconDTO categoryIconDTO = new CategoryIconDTO();
            categoryIconDTO.setId(categoryIcon.getId());
            categoryIconDTO.setPlatformId(categoryIcon.getPlatform().getId());
            categoryIconDTO.setImageId(categoryIcon.getImage().getId());
            categoryIconDTO.setUrl(categoryIcon.getImage().getCdnUrl().concat(categoryIcon.getImage().getPath()));
            return categoryIconDTO;
        }).collect(Collectors.toList()));
        categoryDTO.setPlatforms(category.getPlatforms().stream().map(Platform::getId).collect(Collectors.toList()));
        categoryDTO.setTranslations(category.getCategoryTranslations().stream().map(categoryTranslation -> {
            CategoryTranslationCreateDTO categoryTranslationCreateDTO =new CategoryTranslationCreateDTO();
            categoryTranslationCreateDTO.setId(categoryTranslation.getId());
            categoryTranslationCreateDTO.setName(categoryTranslation.getName());
            categoryTranslationCreateDTO.setSlugs(categoryTranslation.getCategorySlugs().stream().map(categorySlug -> categorySlug.getUrl()).collect(Collectors.toList()));
            categoryTranslationCreateDTO.setLanguageId(categoryTranslation.getLanguage().getId());
            return categoryTranslationCreateDTO;
        }).collect(Collectors.toList()));
        return categoryDTO;
    }

    @Override
    public List<CategoryLightDTO> getCategoriesByPlatform(Long platformId) {
        List<Category> categories = categoryRepository.findDistinctByPlatformsId(platformId);
        return categories.stream().map(c -> modelMapper.map(c, CategoryLightDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void updateGamesOfCategory(Long platformId, Long categoryId, List<Long> games) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException(Category.class, categoryId));
        List<GameDetail> otherPlatformsGameDetails = category.getGameDetails().stream().filter(gameDetail -> !gameDetail.getPlatform().getId().equals(platformId)).collect(Collectors.toList());
        List<GameDetail> gameDetails = gameDetailRepository.findByPlatformIdAndGameIdIn(platformId, games);
        otherPlatformsGameDetails.addAll(gameDetails);
        category.setGameDetails(otherPlatformsGameDetails);
        categoryRepository.save(category);
    }

    @Override
    public void deleteIconFromCategory(Long id, Long iconId) throws FileDeleteException {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(Category.class, id));
        CategoryIcon icon = category.getCategoryIcons().stream().filter(categoryIcon -> categoryIcon.getImage().getId().equals(iconId)).findAny().orElseThrow(() -> new NotFoundException(ImageDTO.class, iconId));
        category.getCategoryIcons().remove(icon);
        fileUploadService.deleteFile(iconId);



    }
}
