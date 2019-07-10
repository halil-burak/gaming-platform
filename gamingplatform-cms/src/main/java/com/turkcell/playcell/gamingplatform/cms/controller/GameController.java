package com.turkcell.playcell.gamingplatform.cms.controller;

import com.turkcell.playcell.gamingplatform.cms.dto.PageDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.game.*;
import com.turkcell.playcell.gamingplatform.cms.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("")
    public Long saveGame(@RequestBody @Valid GameCreateDTO gameCreateDTO) {
        return gameService.saveGame(gameCreateDTO);
    }

    @PutMapping("/{id}")
    public List<Long> updateGame(@PathVariable(name = "id") Long id, @RequestBody @Valid GameUpdateDTO gameUpdateDTO) {
        return gameService.updateGame(id, gameUpdateDTO);
    }

    @GetMapping("/{id}")
    public GameGetOneDTO getGame(@PathVariable(name = "id") Long id) {
        return gameService.getGame(id);
    }

    @GetMapping("/platforms/{platformId}")
    public List<GameDTO> getGamesByPlatform(@PathVariable(name = "platformId") Long platformId) {
        return gameService.getGamesByPlatform(platformId);
    }

    // todo: Query String den oku
    @GetMapping("/platforms/{platformId}/categories/{categoryId}")
    public List<GameDTO> getGamesByPlatformAndCategory(@PathVariable(name = "platformId") Long platformId, @PathVariable(name = "categoryId")Long categoryId) {
        return gameService.getGamesByPlatformAndCategory(platformId, categoryId);
    }

    @GetMapping("")
    public PageDTO<GameGetDTO> getGames(@RequestParam(name = "search", required = false) String search, @RequestParam(name = "pageIndex", required = false) Integer pageIndex,
                                        @RequestParam(name = "size", required = false) Integer size, @RequestParam(name="platforms", required = false) List<Long> platforms,
                                        @RequestParam(name="onlyPublished", required=false) boolean onlyPublished) {
        return gameService.getGames(search, pageIndex, size, platforms, onlyPublished);
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable(name = "id") Long id) {
        gameService.deleteGame(id);
    }


//    @PutMapping("/{gameId}/platforms/{platformId}/publish")
//    public void publishGame(@PathVariable(name = "gameId") Long gameId, @PathVariable(name = "platformId") Long platformId, @RequestBody PublishCreateDTO publishCreateDTO) {
//        gameService.publishGame(gameId, platformId, publishCreateDTO);
//    }
}
