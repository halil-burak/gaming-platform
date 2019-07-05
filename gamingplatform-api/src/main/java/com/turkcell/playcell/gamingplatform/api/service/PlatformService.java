package com.turkcell.playcell.gamingplatform.api.service;

import com.turkcell.playcell.gamingplatform.api.response.PlatformResponse;

public interface PlatformService {
    PlatformResponse getPlatformInfoByPlatformName(String platformName);
}
