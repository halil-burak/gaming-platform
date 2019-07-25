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

    @Value("${turkcell.ldap.url}")
    private String ldapUrl;

    @Value("${turkcell.ldap.managerDn}")
    private String ldapManagerDn;

    @Value("${turkcell.ldap..managerPassword}")
    private String ldapManagerPassword;

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

    public String getLdapUrl() {
        return ldapUrl;
    }

    public String getLdapManagerDn() {
        return ldapManagerDn;
    }

    public String getLdapManagerPassword() {
        return ldapManagerPassword;
    }
}
