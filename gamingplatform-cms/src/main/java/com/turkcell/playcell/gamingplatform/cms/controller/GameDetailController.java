package com.turkcell.playcell.gamingplatform.cms.controller;

import com.turkcell.playcell.gamingplatform.cms.dto.PageDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.game.GameDetailCreateDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.game.GameDetailDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.game.GameDetailPublishDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.game.GameDetailUploadDTO;
import com.turkcell.playcell.gamingplatform.cms.service.GameDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/gameDetails")
public class GameDetailController {

    @Autowired
    GameDetailService gameDetailService;

    @PostMapping("")
    public Long saveGameDetail(@RequestBody @Valid GameDetailCreateDTO gameDetailCreateDTO){
        return gameDetailService.saveGameDetail(gameDetailCreateDTO);
    }

    @PutMapping("/{id}")
    public void updateGameDetail(@PathVariable(name = "id") Long id, @RequestBody @Valid GameDetailUploadDTO gameDetailUploadDTO){
        gameDetailService.updateGameDetail(id, gameDetailUploadDTO);
    }

    @GetMapping("/{id}")
    public GameDetailDTO getGameDetail(@PathVariable(name = "id") Long id){
        return gameDetailService.getGameDetail(id);
    }

    @DeleteMapping("/{id}")
    public void deleteGameDetail(@PathVariable(name = "id") Long id){
        gameDetailService.deleteGameDetail(id);
    }


    @GetMapping("")
    public PageDTO<GameDetailPublishDTO> getGameDetails(@RequestParam(name = "search", required = false) String search, @RequestParam(name = "pageIndex", required = false) Integer pageIndex, @RequestParam(name = "size", required = false) Integer size){
        return gameDetailService.getGameDetails(search, pageIndex, size);
    }
}
