package com.turkcell.playcell.gamingplatform.cms.service.impl;

import com.turkcell.playcell.gamingplatform.cms.ApplicationProperties;
import com.turkcell.playcell.gamingplatform.cms.dto.GameFileDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.PageDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.game.*;
import com.turkcell.playcell.gamingplatform.cms.exception.NotFoundException;
import com.turkcell.playcell.gamingplatform.cms.service.GameService;
import com.turkcell.playcell.gamingplatform.cms.util.StringUtils;
import com.turkcell.playcell.gamingplatform.common.entity.*;
import com.turkcell.playcell.gamingplatform.common.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    @Autowired
    ApplicationProperties applicationProperties;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    LanguageRepository languageRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    GameDetailRepository gameDetailRepository;

    @Autowired
    ModelMapper modelMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long saveGame( GameCreateDTO gameCreateDTO) {
        Game game = new Game();
        game.setName(gameCreateDTO.getName());
        game.setUrl(gameCreateDTO.getUrl());
        if(gameCreateDTO.getGameFileId() != null && gameCreateDTO.getGameFileId() != 0){
            game.setGameFile(new GameFile(gameCreateDTO.getGameFileId() ));
        }
        game.setHideOnCategories(gameCreateDTO.isHideOnCategories());
        game.setScore(gameCreateDTO.getScore());
        game.setFtpAccount(new FtpAccount(gameCreateDTO.getFtpAccountId()));
        game.getGameDetails().addAll(gameCreateDTO.getPlatforms().stream().map(platformId -> {
            GameDetail gameDetail = new GameDetail();
            gameDetail.setGame(game);
            gameDetail.setPlatform(new Platform(platformId));
            gameDetail.getTariffs().addAll(gameCreateDTO.getTariffs().stream().map( tariffId-> new Tariff(tariffId)).collect(Collectors.toList()));
            gameDetail.setPublishDatetime(gameCreateDTO.getPublishDatetime());
            gameDetail.setUnpublishDatetime(gameCreateDTO.getUnpublishDatetime());

            List<Long> categoriesOfPlatform = categoryRepository.findIDsByPlatformsId(platformId);
            gameDetail.getCategories().addAll(gameCreateDTO.getCategories().stream().filter(categoryId -> categoriesOfPlatform.contains(categoryId))
                    .map(categoryId-> new Category(categoryId)).collect(Collectors.toList()));

            List<Long> languagesOfPlatform = languageRepository.findIDsByPlatformsId(platformId);
            gameDetail.getGameDetailTranslations().addAll(gameCreateDTO.getTranslations().stream().filter(translationDTO -> languagesOfPlatform.contains(translationDTO.getLanguageId()))
                    .map(translationDTO -> {
                        GameDetailTranslation gameDetailTranslation = modelMapper.map(translationDTO, GameDetailTranslation.class);
                        gameDetailTranslation.setGameDetail(gameDetail);
                        gameDetailTranslation.getGameSlugs().addAll(translationDTO.getGameSlugs().stream().map(s -> {
                           GameSlug gameSlug = new GameSlug(StringUtils.replaceTurkishChars(s));
                           gameSlug.setGameDetailTranslation(gameDetailTranslation);
                           return gameSlug;
                        }).collect(Collectors.toList()));
                        gameDetailTranslation.getImages().addAll(translationDTO.getImages().stream().map(imageId -> new Image(imageId)).collect(Collectors.toList()));
                        return gameDetailTranslation;
                    }).collect(Collectors.toList()));

            return gameDetail;
        }).collect(Collectors.toList()));
        return gameRepository.save(game).getId();
    }

    @Override
    public List<Long> updateGame(Long id, GameUpdateDTO gameUpdateDTO) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new NotFoundException(Game.class, id));
        game.setName(gameUpdateDTO.getName());
        game.setUrl(gameUpdateDTO.getUrl());

        if(gameUpdateDTO.getGameFileId() != null && gameUpdateDTO.getGameFileId() != 0){
            game.setGameFile(new GameFile(gameUpdateDTO.getGameFileId()));
        }else{
            game.setGameFile(null);
        }

        game.setHideOnCategories(gameUpdateDTO.isHideOnCategories());
        game.setScore(gameUpdateDTO.getScore());

        // delete platforms
        game.getGameDetails().stream().filter(gameDetail -> ! gameUpdateDTO.getPlatforms().contains(gameDetail.getPlatform().getId())).collect(Collectors.toList()).forEach(gameDetail -> game.getGameDetails().remove(gameDetail));

        // creates platform if there is new platform
        List<Long> existingPlatforms = game.getGameDetails().stream().map(detail-> detail.getPlatform().getId()).collect(Collectors.toList());

        gameUpdateDTO.getPlatforms().forEach(platformId -> {
            if (!existingPlatforms.contains(platformId)) {
                GameDetail gameDetail = new GameDetail();
                gameDetail.setGame(game);
                gameDetail.setPlatform(new Platform(platformId));
                game.addGameDetail(gameDetail);
            }
        });
        return game.getGameDetails().stream().map(gameDetail -> gameDetail.getPlatform().getId()).collect(Collectors.toList());
    }

    @Override
    public GameGetOneDTO getGame(Long id) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new NotFoundException(Game.class, id));
        GameGetOneDTO gameGetOneDTO = new GameGetOneDTO();
        gameGetOneDTO.setName(game.getName());
        gameGetOneDTO.setUrl(game.getUrl());
        if(game.getGameFile() != null){
            GameFileDTO gameFileDTO = new GameFileDTO();
            gameFileDTO.setId(game.getGameFile().getId());
            gameFileDTO.setPath(game.getGameFile().getCdnUrl().concat(game.getGameFile().getPath()));
            gameGetOneDTO.setGameFileDTO(gameFileDTO);
        }
        gameGetOneDTO.setHideOnCategories(game.isHideOnCategories());
        gameGetOneDTO.setScore(game.getScore());
        gameGetOneDTO.setFtpAccountId(game.getFtpAccount().getId());
        gameGetOneDTO.setGameDetails(game.getGameDetails().stream().map(gameDetail ->{
            GameDetailLightDTO gameDetailLightDTO = new GameDetailLightDTO();
            gameDetailLightDTO.setId(gameDetail.getId());
            gameDetailLightDTO.setPlatformId(gameDetail.getPlatform().getId());
            return gameDetailLightDTO;
        }).collect(Collectors.toList()));
        return gameGetOneDTO;
    }

    //category-game api
    @Override
    public List<GameDTO> getGamesByPlatform(Long platformId) {
        List<Game> games = gameRepository.findDistinctByGameDetailsPlatformId(platformId);
        return games.stream().map(g -> modelMapper.map(g, GameDTO.class)).collect(Collectors.toList());
    }

    //category-game api
    @Override
    public List<GameDTO> getGamesByPlatformAndCategory(Long platformId, Long categoryId) {
        List<Game> games = gameRepository.findDistinctByGameDetailsPlatformIdAndGameDetailsCategoriesId(platformId, categoryId);
        return games.stream().map(g -> modelMapper.map(g, GameDTO.class)).collect(Collectors.toList());
    }


    @Override
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    @Override
    public PageDTO<GameGetDTO> getGames(String search, Integer pageIndex, Integer size, List<Long> platforms, boolean onlyPublished) {
        if(search != null) { search = search.trim(); }
        if(size == null || size == 0){ size = applicationProperties.getDefaultPageSize(); }
        if(pageIndex == null) { pageIndex = 0;}
        if(platforms != null && platforms.size() == 0){ platforms = null; }

        Page<Game> games = gameRepository.findGameByFilter(platforms, search, onlyPublished, PageRequest.of(pageIndex, size));

        PageDTO<GameGetDTO> pageDTO = new PageDTO();
        pageDTO.setTotalElements(games.getTotalElements());
        pageDTO.setTotalPages(games.getTotalPages());
        pageDTO.setContent(games.getContent().stream().map(g -> {
            GameGetDTO gameGetDTO = new GameGetDTO();
            gameGetDTO.setName(g.getName());
            gameGetDTO.setId(g.getId());
            gameGetDTO.setScore(g.getScore());
            gameGetDTO.setGameDetails(g.getGameDetails().stream().map(gameDetail -> modelMapper.map(gameDetail, GameDetailLightDTO.class)).collect(Collectors.toList()));
            return gameGetDTO;
        }).collect(Collectors.toList()));
        return pageDTO;
    }

}
