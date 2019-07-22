package com.turkcell.playcell.gamingplatform.cms.service;

import com.turkcell.playcell.gamingplatform.cms.dto.platform.PlatformCreateDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.platform.PlatformDTO;
import com.turkcell.playcell.gamingplatform.cms.exception.FileDeleteException;

import java.util.List;

public interface PlatformService {

    Long savePlatform(PlatformCreateDTO platformCreateDTO);

    void updatePlatform(Long id, PlatformCreateDTO platformCreateDTO);

    void deletePlatform(Long id);

    List<PlatformDTO> getPlatforms();

    void deleteLogoFromPlatform(Long id, Long logoImageId) throws FileDeleteException;

}
