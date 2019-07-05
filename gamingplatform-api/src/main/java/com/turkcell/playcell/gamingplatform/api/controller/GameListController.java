package com.turkcell.playcell.gamingplatform.api.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.playcell.gamingplatform.api.config.ApplicationProperties;
import com.turkcell.playcell.gamingplatform.api.dto.GameUrlDTO;
import com.turkcell.playcell.gamingplatform.api.enumtypes.ResponseCodeStrings;
import com.turkcell.playcell.gamingplatform.api.response.DataResponse;
import com.turkcell.playcell.gamingplatform.api.response.GameResponse;
import com.turkcell.playcell.gamingplatform.api.response.PlatformResponse;
import com.turkcell.playcell.gamingplatform.api.service.SubscriptionInfoService;
import com.turkcell.playcell.gamingplatform.api.service.GameService;
import com.turkcell.playcell.gamingplatform.api.service.PlatformService;
import com.turkcell.playcell.gamingplatform.api.util.JWTToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v2.0")
public class GameListController extends BaseController {
	
	private final SubscriptionInfoService subscriptionInfoService;
	
    private final GameService gameService;
	
	private final ApplicationProperties applicationProperties;
	
	private final JWTToken JWTTokenService;

	private final PlatformService platformService;
	
	private final MessageSource messageSource;
	
    @GetMapping("/platforms/{platformName}/games/{slug}")
    public ResponseEntity<Object> getGame(@PathVariable(name = "platformName") String platformName, @PathVariable(name = "slug") String slug, 
    								HttpServletRequest request, HttpServletResponse hresponse,
    								@RequestHeader(name="Accept-Language",required = false) Locale locale) {
    	
    	try {
		    
			String tempToken = super.getAuthorizationHeader(request);
			String language = super.extractLanguageCookie(request);
			String userTariff = "Free";
			
			log.info(language);
			
			if (!ObjectUtils.isEmpty(tempToken)) {
				
				String msisdn = JWTTokenService.validateTempToken(tempToken);
				
				if (ObjectUtils.isEmpty(msisdn)) {
					DataResponse<?> response = DataResponse.createResponse(null, false, ResponseCodeStrings.AUTH_FAIL_TEMP_TOKEN, "Temporary Token Validation Failed");				
		    		log.info("Temporary Token Validation Failed for " + msisdn);
		    		return new ResponseEntity<> (response, HttpStatus.UNAUTHORIZED);
				}
				
				userTariff = subscriptionInfoService.getSubscriptionInfo(msisdn, tempToken);
			}
		        
			GameUrlDTO gameUrlDTO = gameService.getGame(userTariff, platformName, slug, language);
				
			if (ObjectUtils.isEmpty(gameUrlDTO.getUrl())) {
					
				DataResponse<Object> response = DataResponse.createResponse(gameUrlDTO, true, ResponseCodeStrings.GAME_URL_NULL, 
						messageSource.getMessage("game.url.null", null, ObjectUtils.isEmpty(locale) ? applicationProperties.getLocaleLanguage() : locale));
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
		        
			DataResponse<Object> response = DataResponse.createResponse(gameUrlDTO, true, ResponseCodeStrings.SUCCESS, "Game List Succesfully Provided.");
			return new ResponseEntity<>(response, HttpStatus.OK);
			
    	} catch(Exception ex) {
    		log.error("An exception occurred during getGame API call... -> {}", ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    
    @GetMapping("/platforms/{pName}/games")
    public ResponseEntity<Object> getGameList(@PathVariable(name = "pName") String pName, HttpServletRequest request) {

        GameResponse gameResponse = null;
        
        try {
            String language = extractLanguageCookie(request);
            gameResponse = gameService.getGamesByPlatformAndLanguage(pName, language);
        } catch (Exception ex) {
            log.error("An error occurred during getGameList API... -> {}", ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        DataResponse<Object> response = DataResponse.createResponse(gameResponse, true, ResponseCodeStrings.SUCCESS, pName + " Platform Game List Successfully Provided.");
		return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/platforms/{pName}/info")
	public ResponseEntity<Object> getPlatformInfo(@PathVariable(name = "pName") String pName, HttpServletRequest request) {
    	PlatformResponse platformResponse = null;
    	try {
			platformResponse = platformService.getPlatformInfoByPlatformName(pName);
		} catch (Exception ex) {
			log.error("An error occurred during getPlatformInfo API... -> {}", ex);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

    	DataResponse<Object> response = DataResponse.createResponse(platformResponse, true, ResponseCodeStrings.SUCCESS, pName + " Platform Information Successfully Provided");
		return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
