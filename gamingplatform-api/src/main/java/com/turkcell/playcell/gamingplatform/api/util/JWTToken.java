package com.turkcell.playcell.gamingplatform.api.util;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turkcell.playcell.gamingplatform.api.config.ApplicationProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JWTToken {
	
	@Autowired
	ApplicationProperties applicationProperties;

	private long permtokenValidityInMilliseconds;
	private long temptokenValidityInMilliseconds;

	private String permTokenKey = null;
	private String tempTokenKey = null;

	@PostConstruct
	public void init() {
		
		permTokenKey = applicationProperties.getPermToken();
		tempTokenKey = applicationProperties.getTempToken();

		permtokenValidityInMilliseconds = 1000 * Long.parseLong(applicationProperties.getPermTokenExpiry());
		temptokenValidityInMilliseconds = 1000 * Long.parseLong(applicationProperties.getTempTokenExpiry());

		log.info("[Configuration] JWT Permanent Token mechanizm ready for use with {} expire miliseconds", permtokenValidityInMilliseconds);
		log.info("[Configuration] JWT Temporary Token mechanizm ready for use with {} expire miliseconds", temptokenValidityInMilliseconds);
		
	}
	
	public String generatePermanentToken(String msisdn) {

    	SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
    	byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(permTokenKey);
    	Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    	
    	Map<String, Object> claims = new HashMap<String, Object>();
    	claims.put("MSISDN", msisdn);
    	claims.put("SERVICE", "PLAYCELL");
    	
        return Jwts.builder()
        		.setClaims(claims)
        		.setIssuedAt(new Date((System.currentTimeMillis())))
        		.setExpiration(new Date((Instant.now().toEpochMilli() + permtokenValidityInMilliseconds)))
        		.signWith(signatureAlgorithm, signingKey)
                .compact();
    }
    

	public String generateTemporaryToken(String msisdn) {
		
    	SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
    	byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(tempTokenKey);
    	Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

    	Map<String, Object> claims = new HashMap<String, Object>();
    	claims.put("MSISDN", msisdn);
    	claims.put("SERVICE", "PLAYCELL");
    	
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date((System.currentTimeMillis())))
                .setExpiration(new Date((Instant.now().toEpochMilli() + temptokenValidityInMilliseconds)))
                .signWith(signatureAlgorithm, signingKey)
                .compact();
    }
	
    public String validatePermToken(String authToken) {
    	
        try {      
        	
        	SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        	byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(permTokenKey);
        	Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());   	
        	
        	Jws<Claims> parser = Jwts.parser()
                .require("SERVICE", "PLAYCELL")
                .setAllowedClockSkewSeconds(0)
                .setSigningKey(signingKey)
                .parseClaimsJws(authToken);
        	
        	if (parser.getBody().containsKey("MSISDN")) {
        		return (String) parser.getBody().get("MSISDN");
        	}
        	
        	return null;
            
        } catch (SignatureException e) {
            log.error("Invalid JWT signature -> Message: {} ", e);
            return null;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token -> Message: {}", e);
            return null;
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token -> Message: {}", e);
            return null;
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token -> Message: {}", e);
            return null;
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty -> Message: {}", e);
            return null;
        }

    }

    public String validateTempToken(String authToken) {
    	
        try {
        	
        	SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        	byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(tempTokenKey);
        	Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());  
        	
        	Jws<Claims> parser = Jwts.parser()
                .require("SERVICE", "PLAYCELL")
                .setAllowedClockSkewSeconds(0)
                .setSigningKey(signingKey)
                .parseClaimsJws(authToken);
        	
        	if (parser.getBody().containsKey("MSISDN")) {
        		
        		return (String) parser.getBody().get("MSISDN");
        		
        	}
        	
        	return null;
            
        } catch (SignatureException e) {
            log.error("Invalid JWT signature -> Message: {} ", e);
            return null;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token -> Message: {}", e);
            return null;
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token -> Message: {}", e);
            return null;
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token -> Message: {}", e);
            return null;
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty -> Message: {}", e);
            return null;
        }
    }
	
}
