package com.turkcell.playcell.gamingplatform.cms.service.impl;

import com.turkcell.playcell.gamingplatform.cms.ApplicationProperties;
import com.turkcell.playcell.gamingplatform.cms.dto.ImageDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.PageDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.game.*;
import com.turkcell.playcell.gamingplatform.cms.dto.platform.PlatformDTO;
import com.turkcell.playcell.gamingplatform.cms.exception.NotFoundException;
import com.turkcell.playcell.gamingplatform.cms.service.GameDetailService;
import com.turkcell.playcell.gamingplatform.cms.util.StringUtils;
import com.turkcell.playcell.gamingplatform.common.entity.*;
import com.turkcell.playcell.gamingplatform.common.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GameDetailServiceImpl implements GameDetailService {

    private final GameDetailRepository gameDetailRepository;

    private final GameDetailTranslationRepository gameDetailTranslationRepository;

    private final GameRepository gameRepository;

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    private final GameSlugRepository gameSlugRepository;

    private final ApplicationProperties applicationProperties;

    private final EntityManager entityManager;


    @Override
    public Long saveGameDetail(GameDetailCreateDTO gameDetailCreateDTO) {
        Game game = gameRepository.findById(gameDetailCreateDTO.getGameId()).orElseThrow(()-> new NotFoundException(Game.class, gameDetailCreateDTO.getGameId()));
        GameDetail gameDetail = new GameDetail();
        gameDetail.setCategories(gameDetailCreateDTO.getCategories().stream().map(categoryId -> new Category(categoryId)).collect(Collectors.toList()));
        gameDetail.setPlatform(new Platform(gameDetailCreateDTO.getPlatformId()));
        gameDetail.setTariffs(gameDetailCreateDTO.getTariffs().stream().map(tariffId -> new Tariff(tariffId)).collect(Collectors.toList()));
        gameDetail.getGameDetailTranslations().addAll(gameDetailCreateDTO.getGameDetailTranslations().stream().map(g->{
            GameDetailTranslation gameDetailTranslation = new GameDetailTranslation();
            gameDetailTranslation.setId(g.getId());
            gameDetailTranslation.setGameDetail(gameDetail);
            gameDetailTranslation.setLanguage(new Language(g.getLanguageId()));
            gameDetailTranslation.setDescription(g.getDescription());
            gameDetailTranslation.setName(g.getName());
            gameDetailTranslation.getGameSlugs().addAll(g.getGameSlugs().stream().map(slugName -> {
                GameSlug gameSlug = new GameSlug(StringUtils.replaceTurkishChars(slugName));
                gameSlug.setGameDetailTranslation(gameDetailTranslation);
                return gameSlug;
            }).collect(Collectors.toList()));
            gameDetailTranslation.getImages().addAll(g.getImages().stream().map(imageId -> new Image(imageId)).collect(Collectors.toList()));
            gameDetailTranslation.setGameDetail(gameDetail);
            return gameDetailTranslation;

        }).collect(Collectors.toList()));

        gameDetail.setActive(gameDetailCreateDTO.isActive());
        gameDetail.setPublishDatetime(gameDetailCreateDTO.getPublishDatetime());
        gameDetail.setUnpublishDatetime(gameDetailCreateDTO.getUnpublishDatetime());

        game.getGameDetails().add(gameDetail);
        gameRepository.save(game);
        return null;
    }

    @Override
    public void updateGameDetail(Long id, GameDetailUploadDTO gameDetailUploadDTO) {
        GameDetail gameDetail = gameDetailRepository.findById(id).orElseThrow(() -> new NotFoundException(GameDetail.class, id));
        gameDetail.getCategories().clear();
        gameDetail.getCategories().addAll(gameDetailUploadDTO.getCategories().stream().map(categoryId -> new Category(categoryId)).collect(Collectors.toList()));
        gameDetail.getTariffs().clear();
        gameDetail.getTariffs().addAll(gameDetailUploadDTO.getTariffs().stream().map(tariffId -> new Tariff(tariffId)).collect(Collectors.toList()));
        gameDetail.getGameDetailTranslations().clear();
        gameDetail.getGameDetailTranslations().addAll(gameDetailUploadDTO.getGameDetailTranslations().stream().map(g->{
            GameDetailTranslation gameDetailTranslation;
            if(g.getId() == null || g.getId().equals(0)){
                gameDetailTranslation = new GameDetailTranslation();
                gameDetailTranslation.setGameDetail(gameDetail);
            }
            else{
                gameDetailTranslation = gameDetailTranslationRepository.findById(g.getId()).get();
            }
            gameDetailTranslation.setLanguage(new Language(g.getLanguageId()));
            gameDetailTranslation.setDescription(g.getDescription());
            gameDetailTranslation.setName(g.getName());

            List<String> gameSlugs = g.getGameSlugs().stream().map(gameSlug -> StringUtils.replaceTurkishChars(gameSlug)).collect(Collectors.toList());

            gameDetailTranslation.getGameSlugs().stream().filter(gameSlug -> ! gameSlugs.contains(gameSlug.getUrl())).collect(Collectors.toList())
                    .forEach(gameSlug -> gameDetailTranslation.removeGameSlug(gameSlug));

            List<String> existingSlugs = gameDetailTranslation.getGameSlugs().stream().map(gameSlug-> gameSlug.getUrl()).collect(Collectors.toList());
            gameSlugs.forEach(s -> {
                if (!existingSlugs.contains(s)) {
                    GameSlug gs = new GameSlug();
                    gs.setUrl(s);
                    gs.setGameDetailTranslation(gameDetailTranslation);
                    gameDetailTranslation.addGameSlug(gs);
                }
            });
            gameDetailTranslation.getImages().clear();
            gameDetailTranslation.getImages().addAll(g.getImages().stream().map(imageId -> new Image(imageId)).collect(Collectors.toList()));
            return gameDetailTranslation;

        }).collect(Collectors.toList()));

        gameDetail.setActive(gameDetailUploadDTO.isActive());
        gameDetail.setPublishDatetime(gameDetailUploadDTO.getPublishDatetime());
        gameDetail.setUnpublishDatetime(gameDetailUploadDTO.getUnpublishDatetime());
        gameDetailRepository.save(gameDetail);
    }

    @Override
    public GameDetailDTO getGameDetail(Long id) {
        GameDetail gameDetail = gameDetailRepository.findById(id).orElseThrow(()-> new NotFoundException(GameDetail.class, id));
        GameDetailDTO gameDetailDTO = new GameDetailDTO();
        gameDetailDTO.setPlatformId(gameDetail.getPlatform().getId());
        gameDetailDTO.setCategories(gameDetail.getCategories().stream().map(Category::getId).collect(Collectors.toList()));
        gameDetailDTO.setTariffs(gameDetail.getTariffs().stream().map(Tariff::getId).collect(Collectors.toList()));
        gameDetailDTO.setGameDetailTranslations(gameDetail.getGameDetailTranslations().stream().map(gameDetailTranslation -> {
            GameDetailTranslationDTO gameDetailTranslationDTO = new GameDetailTranslationDTO();
            gameDetailTranslationDTO.setId(gameDetailTranslation.getId());
            gameDetailTranslationDTO.setName(gameDetailTranslation.getName());
            gameDetailTranslationDTO.setGameSlugs(gameDetailTranslation.getGameSlugs().stream().sorted(Comparator.comparing(GameSlug::getCreatedDate)).map(gameSlug -> gameSlug.getUrl()).collect(Collectors.toList()));
            gameDetailTranslationDTO.setDescription(gameDetailTranslation.getDescription());
            gameDetailTranslationDTO.setLanguageId(gameDetailTranslation.getLanguage().getId());
            gameDetailTranslationDTO.setImages(gameDetailTranslation.getImages().stream().map(image -> {
                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setId(image.getId());
                imageDTO.setSizeId(image.getSizeId());
                imageDTO.setFullPath(image.getCdnUrl().concat(image.getPath()));
                return imageDTO;
            }).collect(Collectors.toList()));
            return gameDetailTranslationDTO;
        }).collect(Collectors.toList()));
        gameDetailDTO.setActive(gameDetail.isActive());
        gameDetailDTO.setPublishDatetime(gameDetail.getPublishDatetime());
        gameDetailDTO.setUnpublishDatetime(gameDetail.getUnpublishDatetime());
        return gameDetailDTO;
    }

    @Override
    public void deleteGameDetail(Long id) {
        gameDetailRepository.deleteById(id);
    }

    @Override
    public PageDTO<GameDetailPublishDTO> getGameDetails(String search, Integer pageIndex, Integer size) {
        if(search != null) { search = search.trim(); }
        if(size == null || size == 0){ size = applicationProperties.getDefaultPageSize(); }
        if(pageIndex == null) { pageIndex = 0;}

        Page<GameDetail> gameDetails = gameDetailRepository.findByNameIgnoreCaseContaining(search, PageRequest.of(pageIndex, size));

        PageDTO<GameDetailPublishDTO> pageDTO = new PageDTO();
        pageDTO.setTotalElements(gameDetails.getTotalElements());
        pageDTO.setTotalPages(gameDetails.getTotalPages());
        pageDTO.setContent(gameDetails.getContent().stream().map(g -> {
            GameDetailPublishDTO gameDetailPublishDTO = new GameDetailPublishDTO();
            gameDetailPublishDTO.setId(g.getGame().getId());
            gameDetailPublishDTO.setGameName(g.getGame().getName());
            gameDetailPublishDTO.setPlatform(modelMapper.map(g.getPlatform(), PlatformDTO.class));
            gameDetailPublishDTO.setPublishDatetime(g.getPublishDatetime());
            gameDetailPublishDTO.setUnpublishDatetime(g.getUnpublishDatetime());
            return gameDetailPublishDTO;
        }).collect(Collectors.toList()));
        return pageDTO;
    }
}
