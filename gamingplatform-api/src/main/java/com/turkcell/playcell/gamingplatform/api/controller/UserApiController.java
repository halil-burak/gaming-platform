package com.turkcell.playcell.gamingplatform.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.playcell.gamingplatform.api.dto.TariffDTO;
import com.turkcell.playcell.gamingplatform.api.enumtypes.ResponseCodeStrings;
import com.turkcell.playcell.gamingplatform.api.request.AuthorizationRequest;
import com.turkcell.playcell.gamingplatform.api.response.DataResponse;
import com.turkcell.playcell.gamingplatform.api.response.TokenResponse;
import com.turkcell.playcell.gamingplatform.api.service.SubscriptionInfoService;
import com.turkcell.playcell.gamingplatform.api.service.impl.TariffServiceImpl;
import com.turkcell.playcell.gamingplatform.api.util.JWTToken;
import com.turkcell.playcell.gamingplatform.common.entity.Tariff;
import com.turkcell.playcell.gamingplatform.mobileconnect.model.MobileConnectUserInfoResponse;
import com.turkcell.playcell.gamingplatform.mobileconnect.service.MobileConnectService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v2.0")
public class UserApiController extends BaseController {

	private final MobileConnectService mobileConnectService;
	
	private final SubscriptionInfoService subscriptionInfoService;
	
	private final TariffServiceImpl tariffService;
	
	private final JWTToken JWTTokenService;
	
    private final ModelMapper modelMapper;
	
    @GetMapping("")
    public String home() {
        log.info("PLAYCELL GAME LIST/USER API v2.0 Home Page Visited !!!");
        return "Welcome to Playcell User Authantication and Platform Game List Provider API";
    }
	
	// Mobile Connect Authorization Code Verification and Return Permanent Token
	@PostMapping (path = "/authorization", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> authorization (HttpServletRequest request, HttpServletResponse hresponse, @RequestBody AuthorizationRequest body) {
		
		try {
				if (!super.checkAuthorizationHeader(request)) {
					DataResponse<Object> response = DataResponse.createResponse(null, false, ResponseCodeStrings.BASIC_AUTH_FAIL, "Basic Authorization Failed");
					log.error("Basic Authorization Failed.");
					return new ResponseEntity<> (response, HttpStatus.UNAUTHORIZED);	
			    }
			    	
			    MobileConnectUserInfoResponse userInfoResp = mobileConnectService.authenticateMobileConnect(body.getAccesscode(), body.getRedirecturl());

				if (userInfoResp.getPhone_number_verified()) {
								
					String permToken = JWTTokenService.generatePermanentToken(userInfoResp.getPhone_number());
								
					if (!ObjectUtils.isEmpty(permToken)) {		
						TokenResponse respdata = TokenResponse.builder().token(permToken).msisdn(userInfoResp.getPhone_number()).build();
						DataResponse<TokenResponse> responsesuccess = DataResponse.createSuccessTokenResponse(respdata, "Permanent Token is successfully provided.");
						log.info("Permanent Token is successfully provided for " + userInfoResp.getPhone_number());
						return new ResponseEntity<> (responsesuccess, HttpStatus.OK);
					}
				}
			
			 DataResponse<?> response = DataResponse.createResponse(null, false, ResponseCodeStrings.PROCESS_ERROR, "Error Occured During Client Validation Process !!!");		
			 log.info("Error Occured During Client Validation Process !!!");
			 return new ResponseEntity<> (response, HttpStatus.NOT_FOUND);
			
		} catch (Exception ex) {

			DataResponse<?> response = DataResponse.createResponse(null, false, ResponseCodeStrings.EXCEPTION_ERROR, "Mobile Connect Validation Failed");
			log.error("Mobile Connect Validation Failed");
			return new ResponseEntity<> (response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	// Permanent Token Validation and Temporary Token Provide Service 
	@PostMapping (path="/login", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login (HttpServletRequest request, HttpServletResponse hresponse, @RequestHeader HttpHeaders headers) {
		
		//String msisdn = super.getPhoneNumber(request);
		
		String msisdn = null;
		
		try {
		    	
			/*if (!super.checkMsisdnFormat(msisdn)) {
				DataResponse<?> response = DataResponse.createResponse(null, false, ResponseCodeStrings.MSISDN_FORMAT_ERROR, "MSISDN Format Check Failed");
				log.info("MSISDN Format Check Failed for " + msisdn);
				return new ResponseEntity<> (response, HttpStatus.BAD_REQUEST);
			}*/
		    
			String permToken = super.getAuthorizationHeader(request);
			
		    if (!ObjectUtils.isEmpty(permToken)) {
		    	
		    	msisdn = JWTTokenService.validatePermToken(permToken);
		    	
			    	if (ObjectUtils.isEmpty(msisdn)) {
			    		DataResponse<?> response = DataResponse.createResponse(null, false, ResponseCodeStrings.AUTH_FAIL_PERM_TOKEN, "Permanent Token Validation Failed");				
						log.info("Permanent Token Validation Failed for " + msisdn);
			    		return new ResponseEntity<> (response, HttpStatus.UNAUTHORIZED);
			    	}
			    	
			    	String tempToken = JWTTokenService.generateTemporaryToken(msisdn);
			    	
			    	if (!ObjectUtils.isEmpty(tempToken)) {
			    		TokenResponse respdata = TokenResponse.builder().token(tempToken).msisdn(msisdn).build();
						DataResponse<TokenResponse> responsesuccess = DataResponse.createSuccessTokenResponse(respdata, "Temporary Token is successfully provided.");
						log.info("Temporary Token is successfully provided for " + msisdn);
						return new ResponseEntity<> (responsesuccess, HttpStatus.OK);
			    	}
		    }
		    
		    DataResponse<?> response = DataResponse.createResponse(null, false, ResponseCodeStrings.PROCESS_ERROR, "Error Occured During Client Validation Process !!!");		
			log.info("Error Occured During Client Validation Process !!! " + msisdn);
		    return new ResponseEntity<> (response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		} catch (Exception ex) {
			
			DataResponse<?> response = DataResponse.createResponse(null, false, ResponseCodeStrings.AUTH_FAIL_PERM_TOKEN, "Permanent Token Validation Failed");
			log.error("Permanent Token Validation Failed for " + msisdn);
			return new ResponseEntity<> (response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping (path="/getSubscriptionInfo", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getSubscriptionInfo (HttpServletRequest request) {
		
		//String msisdn = super.getPhoneNumber(request);
		
		String msisdn = null;
		
		try {		    
	    	
			/*if (!super.checkMsisdnFormat(msisdn)) {
				DataResponse<?> response = DataResponse.createResponse(null, false, ResponseCodeStrings.MSISDN_FORMAT_ERROR, "MSISDN Format Check Failed");
				log.info("MSISDN Format Check Failed for " + msisdn);
				return new ResponseEntity<> (response, HttpStatus.BAD_REQUEST);
			}*/
		    
			String tempToken = super.getAuthorizationHeader(request);
			
		    if (!ObjectUtils.isEmpty(tempToken)) {
		    	
		    	msisdn = JWTTokenService.validateTempToken(tempToken);
		    	
		    	if (ObjectUtils.isEmpty(msisdn)) {
		    		DataResponse<?> response = DataResponse.createResponse(null, false, ResponseCodeStrings.AUTH_FAIL_TEMP_TOKEN, "Temporary Token Validation Failed");				
		    		log.info("Temporary Token Validation Failed for " + msisdn);
		    		return new ResponseEntity<> (response, HttpStatus.UNAUTHORIZED);
		    	}
		    	
		    	String userTariff = subscriptionInfoService.getSubscriptionInfo(msisdn, tempToken);
		    	
		    	Tariff tariff = tariffService.findByName(userTariff);
		    	TariffDTO tariffDTO = new TariffDTO();
		    	
	            if (tariff != null) {
	            	tariffDTO = modelMapper.map(tariff, TariffDTO.class);
	            } else {
	                tariffDTO = new TariffDTO("Free", 0, "Free", "Free", 0L, 0L);
	            }

	            DataResponse<TariffDTO> response = DataResponse.createResponse(tariffDTO, true, ResponseCodeStrings.SUCCESS, "Package Query is successfully done.");
	            log.info("Package Query successfully done for " + msisdn);
	            return new ResponseEntity<Object>(response, HttpStatus.OK);
		    }
			
		} catch (Exception ex) {
			
			DataResponse<?> response = DataResponse.createResponse(null, false, ResponseCodeStrings.PACKAGE_QUERY_ERROR, "Exception Occured During Package Query !!!");
			log.error("Exception Occured During Package Query for " + msisdn);
			return new ResponseEntity<> (response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return null;
	}
}
