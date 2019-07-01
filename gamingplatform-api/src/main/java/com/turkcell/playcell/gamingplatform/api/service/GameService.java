package com.turkcell.playcell.gamingplatform.api.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.turkcell.playcell.gamingplatform.api.config.ApplicationProperties;
import com.turkcell.playcell.gamingplatform.api.util.GenerateTicket;
import org.springframework.beans.factory.annotation.Autowired;
import com.turkcell.playcell.gamingplatform.api.dto.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.turkcell.playcell.gamingplatform.api.dto.CategoryDTO;
import com.turkcell.playcell.gamingplatform.common.entity.Category;
import com.turkcell.playcell.gamingplatform.common.entity.CategorySlug;
import com.turkcell.playcell.gamingplatform.common.entity.CategoryTranslation;
import com.turkcell.playcell.gamingplatform.common.entity.Game;
import com.turkcell.playcell.gamingplatform.common.entity.GameDetail;
import com.turkcell.playcell.gamingplatform.common.entity.GameDetailTranslation;
import com.turkcell.playcell.gamingplatform.common.entity.GameSlug;
import com.turkcell.playcell.gamingplatform.common.entity.Tariff;
import com.turkcell.playcell.gamingplatform.common.repository.CategoryRepository;
import com.turkcell.playcell.gamingplatform.common.repository.CategorySlugRepository;
import com.turkcell.playcell.gamingplatform.common.repository.CategoryTranslationRepository;
import com.turkcell.playcell.gamingplatform.common.repository.GameDetailRepository;
import com.turkcell.playcell.gamingplatform.common.repository.GameDetailTranslationRepository;
import com.turkcell.playcell.gamingplatform.common.repository.GameRepository;
import com.turkcell.playcell.gamingplatform.common.repository.GameSlugRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class GameService implements IGameService {
	
    private final GameRepository gameRepository;

    private final GameDetailRepository gameDetailRepository;

    private final CategoryRepository categoryRepository;

    private final GameSlugRepository gameSlugRepository;

    private final GameDetailTranslationRepository gameDetailTranslationRepository;

    private final CategoryTranslationRepository categoryTranslationRepository;

    private final CategorySlugRepository categorySlugRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Cacheable(value = "games", key = "{ #platformName, #language }")
    public GameResponse getGamesByPlatformAndLanguage(String platformName, String language) {

        Instant datetime = Instant.now();
        
        List<GameDTO> gameList = gameDetailRepository.findGameByPlatformName(platformName, language, datetime).stream().map(game -> {

            GameDTO gameDTO = new GameDTO();
            
            gameDTO.setId(game.getId());
            gameDTO.setName(game.getName());
            gameDTO.setHideOnCategories(game.isHideOnCategories());
            gameDTO.setScore(game.getScore());
            
            List<GameDetail> gameDetails = gameDetailRepository.findByPlatformNameEqualsIgnoreCaseAndGameIdAndIsActiveTrue(platformName, game.getId());
            
            for (GameDetail gd: gameDetails) {
                
            	gameDTO.setPlatform(gd.getPlatform().getName());
                gameDTO.setPackages(gd.getTariffs().stream().map(tariff -> {
                    String p = tariff.getName();
                    return p;
                }).collect(Collectors.toList()));
                
                // to get the minimum level in tariffs, if no tariff defined the min level is 0
                int subscription = gd.getTariffs().stream()
                        .mapToInt(Tariff::getGrade)
                        .min()
                        .orElse(0);

                gameDTO.setSubscription(subscription);

                List<Category> categories = gd.getCategories().stream().filter(category -> !category.isTag()).collect(Collectors.toList());
                gameDTO.setCategories(categories.stream().map(category -> {
                    Long categoryId = category.getId();
                    return categoryId;
                }).collect(Collectors.toList()));

                GameDetailTranslation gdt = gameDetailTranslationRepository.findByLanguageShortNameEqualsIgnoreCaseAndGameDetailId(language, gd.getId());
                if (gdt != null) {
                    gameDTO.setImages(gdt.getImages().stream().map(image -> {
                        ImageDTO imageDTO = new ImageDTO();
                        imageDTO.setSizeId(image.getSizeId());
                        imageDTO.setUrl(image.getCdnUrl() + image.getPath());
                        return imageDTO;
                    }).collect(Collectors.toList()));

                    // the slug with the most up to date create date is added to the response
                    GameSlug lastSlug = gameSlugRepository.findFirst1ByGameDetailTranslation_IdOrderByCreatedDateDesc(gdt.getId());
                    if (lastSlug != null) {
                        String slug = lastSlug.getUrl();
                        gameDTO.setSlug(slug);
                    }

                    gameDTO.setTitle(gdt.getName());
                    gameDTO.setDescription(gdt.getDescription());
                }
            }
            return gameDTO;
        }).collect(Collectors.toList());

        List<CategoryDTO> categories = categoryRepository.findDistinctByPlatformsNameEqualsIgnoreCaseAndIsTagFalse(platformName).stream().map(category -> {
            
        	CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setName(category.getName());
            categoryDTO.setVisible(category.isVisible());

            CategoryTranslation ct = categoryTranslationRepository.findByLanguageShortNameEqualsIgnoreCaseAndCategoryId(language, category.getId());
            if (ct != null) {
                List<CategorySlug> slugs = categorySlugRepository.findByCategoryTranslation_Id(ct.getId());
                slugs.sort(Comparator.comparing(CategorySlug::getCreatedDate).reversed());
                String defaultSlug = slugs.get(0).getUrl();
                categoryDTO.setSlug(defaultSlug);
                categoryDTO.setOldSlugs(slugs.stream().map(cs -> {
                    String s = cs.getUrl();
                    return s;
                }).collect(Collectors.toList()).stream().filter(x -> !x.equalsIgnoreCase(defaultSlug)).collect(Collectors.toList()));
                categoryDTO.setTitle(ct.getName());
            }
            return categoryDTO;
        }).collect(Collectors.toList());

        Map<String, List<Long>> tagMap = new HashMap<>();
        
        List<TagDTO> tagCategories = categoryRepository.findDistinctByPlatformsNameEqualsIgnoreCaseAndIsTagTrue(platformName).stream().map(category -> {
            
        	TagDTO tagDTO = new TagDTO();
            tagMap.put(category.getName().toLowerCase(Locale.ROOT).replace(" ", ""), gameRepository.findDistinctByGameDetailsPlatformNameEqualsIgnoreCaseAndGameDetailsCategoriesId(platformName, category.getId()).stream().map(game -> {
                return game.getId();
            }).collect(Collectors.toList()));
            tagDTO.setTags(tagMap);
            
            return tagDTO;
        }).collect(Collectors.toList());

        GameResponse response = GameResponse.builder()
        		.tags(tagMap)
        		.games(gameList)
        		.categories(categories)
        		.thumbnailDomain("https://gaming.tcellcdn.net/")
        		.timestamp(LocalDateTime.now())
        		.build();

        return response;
    }

    @Cacheable(value = "game", key = "{#platformName, #slug, #userTariff, #language}")
    public GameUrlDTO getGame(String userTariff, String platformName, String slug, String language) {
    	
    	try {
    		Game game = gameRepository.findGameUrl(platformName, language, slug);
            if(game == null ){ return null; }
            
            GameUrlDTO gameUrlDTO = new GameUrlDTO();
            gameUrlDTO.setId(game.getId());
            
            GameDetail gameDetail = game.getGameDetails().stream().filter(gd -> gd.getPlatform().getName().equalsIgnoreCase(platformName)).findFirst().get();
            
            GameDetailTranslation gameDetailTranslation = gameDetail.getGameDetailTranslations().stream().filter(gdt -> gdt.getLanguage().getShortName().equalsIgnoreCase(language)).findFirst().get();
            gameDetailTranslation.getGameSlugs().sort(Comparator.comparing(GameSlug::getCreatedDate).reversed());
            
            gameUrlDTO.setDefaultSlug(gameDetailTranslation.getGameSlugs().get(0).getUrl());
            
            if(gameDetail.getTariffs().size() == 0 || gameDetail.getTariffs().stream().filter(t -> t.getName().equalsIgnoreCase(userTariff)).findFirst().isPresent()){
                if(game.getGameFile() != null) {
                    String urlWithToken = GenerateTicket.CreateStaticTicket(game.getUrl(),3600, applicationProperties.getCdnPrivateKey());
                    gameUrlDTO.setUrl(urlWithToken);
                }
            }
            
            return gameUrlDTO;
            
    	} catch (Exception ex) {
    		return null;
    	}
    }
}
