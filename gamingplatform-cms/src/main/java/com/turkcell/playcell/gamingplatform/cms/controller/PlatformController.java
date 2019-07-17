package com.turkcell.playcell.gamingplatform.cms.controller;

import com.turkcell.playcell.gamingplatform.cms.dto.platform.PlatformCreateDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.platform.PlatformDTO;
import com.turkcell.playcell.gamingplatform.cms.exception.FileDeleteException;
import com.turkcell.playcell.gamingplatform.cms.service.PlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/platforms")
@RequiredArgsConstructor
public class PlatformController {

    private final PlatformService platformService;

    @PostMapping("")
    public Long savePlatform(@RequestBody @Valid PlatformCreateDTO platformCreateDTO) {
        return platformService.savePlatform(platformCreateDTO);
    }

    @PutMapping("/{id}")
    public void updatePlatform(@PathVariable(name = "id") Long id, @RequestBody @Valid PlatformCreateDTO platformCreateDTO) {
        platformService.updatePlatform(id, platformCreateDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePlatform(@PathVariable(name = "id")Long id) {
        platformService.deletePlatform(id);
    }

    @GetMapping("")
    public List<PlatformDTO> getPlatforms() {
        return platformService.getPlatforms();
    }

    @DeleteMapping("/{id}/logo/{logoId}")
    public void deleteLogoFromPlatform(@PathVariable(name = "id") Long id, @PathVariable(name = "logoId") Long logoId) throws FileDeleteException {
        platformService.deleteLogoFromPlatform(id, logoId);
    }
}
