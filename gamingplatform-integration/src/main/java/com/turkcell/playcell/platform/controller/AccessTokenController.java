package com.turkcell.playcell.platform.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.playcell.platform.enumtypes.ResponseCodeStrings;
import com.turkcell.playcell.platform.model.AccessTokenBody;
import com.turkcell.playcell.platform.model.AccessTokenResponse;
import com.turkcell.playcell.platform.model.BaseResponse;
import com.turkcell.playcell.platform.security.JwtProvider;
import com.turkcell.playcell.platform.token.MobileConnectAccessToken;
import com.turkcell.playcell.platform.token.MobileConnectAuthKey;

@RestController
@RequestMapping("/api/v2.0")
public class AccessTokenController extends BaseController {
	
	public static String authKeyValue;
	
	private Boolean CheckMsisdnFormat (String msisdn) {
		
		if (msisdn.matches("[0-9]+")) {
			if (msisdn.length() == 10) {
				return true;
			}
		}
		
		return false;
	}
	
	
	// Mobile Connect Authorization Code Verification and Return Permanent Token
	@PostMapping (path = "/Authorization", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> Authorization (@RequestHeader HttpHeaders headers, @RequestBody AccessTokenBody body) {
		
		try {
			// Basic Authentication Check
			if (headers.containsKey("Authorization")) {
				
		    	if (headers.getFirst("Authorization").contains(" ")) {
		    		
			    	String [] authTokenParts = headers.getFirst("Authorization").split(" ");
			    	String parm = authTokenParts[1];
			    	
			    	if (parm.compareTo(authKeyValue) != 0) {
			    		
			    		BaseResponse response = new BaseResponse();
						
						response = prepareErrorResponse("Basic Authorization Failed", ResponseCodeStrings.BASIC_AUTH_FAIL);
						
						return new ResponseEntity<> (response, HttpStatus.UNAUTHORIZED);	
			    	}
			    	
			    	// Mobile Connect Access Token Get
			    	MobileConnectAuthKey authKeyinstance = new MobileConnectAuthKey(body.getAccessCode(), body.getRedirectURL());
					String accessToken = authKeyinstance.getAccessToken();
					
					if (accessToken == null) {
						
						BaseResponse response = new BaseResponse();
						response = prepareErrorResponse("Mobile Connect Validation Failed", ResponseCodeStrings.AUTH_FAIL_MOBCON_TOKEN);
							
						return new ResponseEntity<> (response, HttpStatus.UNAUTHORIZED);
					}
					
					// Validate User Info Check
					MobileConnectAccessToken userInfo = new MobileConnectAccessToken(accessToken);
					String msisdn = userInfo.validateUserInfo();
					
					if (msisdn != null) {
								
						JwtProvider jwt = new JwtProvider();
						String permToken = jwt.generatePermanentToken(msisdn);
								
						if (permToken != null) {		
							
							AccessTokenResponse responsesuccess = prepareSuccessTokenResponse(permToken, msisdn, "Permanent Token is successfully provided.");				
									
							return new ResponseEntity<> (responsesuccess, HttpStatus.OK);
						}
					}
		    	}
			}
			
			BaseResponse response = new BaseResponse();
			
			response = prepareErrorResponse("Error Occured During Client Validation Process !!!", ResponseCodeStrings.PROCESS_ERROR);
			
			return new ResponseEntity<> (response, HttpStatus.NOT_FOUND);
			
		} catch (Exception ex) {
			
			BaseResponse response = prepareErrorResponse("Mobile Connect Validation Failed", ResponseCodeStrings.EXCEPTION_ERROR);
			
			return new ResponseEntity<> (response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	// Permanent Token Validation and Temporary Token Provide Service 
	@PostMapping (path="/Login", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> Login (@RequestHeader HttpHeaders headers) {
		
		try {
			 
		    if (headers.containsKey("turkcell-playcell-msisdn") && headers.containsKey("Authorization")) {

		    	String msisdn = headers.getFirst("turkcell-playcell-msisdn");
		    	
				if (!this.CheckMsisdnFormat(msisdn)) {
					
					BaseResponse response = prepareErrorResponse("MSISDN Format Check Failed", ResponseCodeStrings.MSISDN_FORMAT_ERROR);
					
					return new ResponseEntity<> (response, HttpStatus.BAD_REQUEST);
				}
		    	
		    	if (headers.getFirst("Authorization").contains(" ")) {
		    		
			    	String [] authTokenParts = headers.getFirst("Authorization").split(" ");
			    	String permToken = authTokenParts[1];
			    	
			    	JwtProvider jwt = new JwtProvider();
			    	
			    	if (!jwt.validatePermToken(msisdn, permToken)) {
			    		
			    		BaseResponse response = new BaseResponse();
						response = prepareErrorResponse("Permanent Token Validation Failed", ResponseCodeStrings.AUTH_FAIL_PERM_TOKEN);					
						
						return new ResponseEntity<> (response, HttpStatus.UNAUTHORIZED);
			    	}
			    	
			    	String tempToken = jwt.generateTemporaryToken(msisdn);
			    	
			    	if (tempToken != null) {
						
			    		AccessTokenResponse responsesuccess = prepareSuccessTokenResponse(tempToken, msisdn, "Temporary Token is successfully provided.");		
						
						return new ResponseEntity<> (responsesuccess, HttpStatus.OK);
			    	}
		    	}
		    }
		    
			BaseResponse response = new BaseResponse();
			
			response = prepareErrorResponse("Error Occured During Permanent Token Validation Process !!!", ResponseCodeStrings.PROCESS_ERROR);
			
			return new ResponseEntity<> (response, HttpStatus.NOT_FOUND);
			
		} catch (Exception ex) {
			
			BaseResponse response = prepareErrorResponse("Permanent Token Validation Failed", ResponseCodeStrings.AUTH_FAIL_PERM_TOKEN);
			
			return new ResponseEntity<> (response, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping (path="/GetSubscriptionInfoV2", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> GetSubscriptionInfo (@RequestHeader HttpHeaders headers) {
		
		try {
			
		    if (headers.containsKey("turkcell-playcell-msisdn") && headers.containsKey("Authorization")) {

		    	String msisdn = headers.getFirst("turkcell-playcell-msisdn");
		    	
				if (!this.CheckMsisdnFormat(msisdn)) {
					
					BaseResponse response = prepareErrorResponse("MSISDN Format Check Failed", ResponseCodeStrings.MSISDN_FORMAT_ERROR);
					
					return new ResponseEntity<> (response, HttpStatus.BAD_REQUEST);
				}
		    	
				if (headers.getFirst("Authorization").contains(" ")) {
		    		
			    	String [] authTokenParts = headers.getFirst("Authorization").split(" ");
			    	String tempToken = authTokenParts[1];
			    	
			    	JwtProvider jwt = new JwtProvider();
			    	
			    	if (!jwt.validateTempToken(msisdn, tempToken)) {
			    		
			    		BaseResponse response = new BaseResponse();
			    		
						response = prepareErrorResponse("Temporary Token Validation Failed", ResponseCodeStrings.AUTH_FAIL_TEMP_TOKEN);
						
						return new ResponseEntity<> (response, HttpStatus.UNAUTHORIZED);
			    	}
			    	
			    	// Msisdn'den package query will be implemented soon
					//AccessTokenResponse responsesuccess = prepareSuccessTokenResponse("Paket Bilgisi", "Temporary Token is successfully provided.");
					
					return new ResponseEntity<> (HttpStatus.OK);
				}
		    }
		    
			BaseResponse response = new BaseResponse();
			
			response = prepareErrorResponse("Error Occured During Package Query Process !!!", ResponseCodeStrings.PROCESS_ERROR);
			
			return new ResponseEntity<> (response, HttpStatus.NOT_FOUND);
			
		} catch (Exception ex) {
			
			BaseResponse response = prepareErrorResponse("Exception Occured During Package Query !!!", ResponseCodeStrings.PACKAGE_QUERY_ERROR);
			
			return new ResponseEntity<> (response, HttpStatus.BAD_REQUEST);
		}
	}

}
