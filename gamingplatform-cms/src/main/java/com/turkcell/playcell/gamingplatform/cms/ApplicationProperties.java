package com.turkcell.playcell.gamingplatform.cms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationProperties {

    @Value("${spring.data.rest.default-page-size}")
    private int defaultPageSize;

    @Value("${cms.jwt.secretKey}")
    private String jwtSecret;

    @Value("${cms.jwt.expiration}")
    private long jwtExpiration;

    @Value("${spring.servlet.multipart.location}")
    private String tmpFolder;

    public int getDefaultPageSize() {
        return defaultPageSize;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public long getJwtExpiration() {
        return jwtExpiration;
    }

    public String getTmpFolder() {
        return tmpFolder;
    }
}
