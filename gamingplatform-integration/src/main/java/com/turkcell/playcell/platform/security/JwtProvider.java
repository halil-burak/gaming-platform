package com.turkcell.playcell.platform.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    
    public static String permTokenKey;
    public static String tempTokenKey;

	public String generatePermanentToken(String msisdn) {

    	SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
    	byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(permTokenKey);
    	Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    	
    	Map<String, Object> claims = new HashMap<String, Object>();
    	claims.put("MSISDN", msisdn);
    	claims.put("SERVICE", "PLAYCELL");
    	
    	long time = 31557600000L;
    	
        return Jwts.builder()
        		.setClaims(claims)
        		.setIssuedAt(new Date((System.currentTimeMillis())))
        		.setExpiration(new Date((Instant.now().toEpochMilli() + time)))
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
    	
    	long time = 1800000L;
    	
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date((System.currentTimeMillis())))
                .setExpiration(new Date((Instant.now().toEpochMilli() + time)))
                .signWith(signatureAlgorithm, signingKey)
                .compact();
    }
	
    public boolean validatePermToken(String req_msisdn, String authToken) {
    	
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
        		
        		String msisdn = (String) parser.getBody().get("MSISDN");
        		
        		if (req_msisdn.compareTo(msisdn) == 0) {
        			return true;
        		}
        		
        	}
            
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }
        
		return false;
    }

    public boolean validateTempToken(String req_msisdn, String authToken) {
    	
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
        		
        		String msisdn = (String) parser.getBody().get("MSISDN");
        		
        		if (req_msisdn.compareTo(msisdn) == 0) {
        			return true;
        		}
        		
        	}
            
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }
        
		return false;
    }

}