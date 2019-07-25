package com.turkcell.playcell.gamingplatform.cms.config;

import com.turkcell.playcell.gamingplatform.cms.ApplicationProperties;
import com.turkcell.playcell.gamingplatform.cms.security.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private  JwtTokenFilter jwtTokenFilter;

    private ApplicationProperties applicationProperties;

    @Autowired
    public WebSecurityConfig(@Lazy JwtTokenFilter jwtTokenFilter, ApplicationProperties applicationProperties ) {
        this.jwtTokenFilter = jwtTokenFilter;
        this.applicationProperties = applicationProperties;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Entry points
        http.authorizeRequests().antMatchers("/login").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/v2/api-docs")
                .antMatchers("/configuration/ui")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/configuration/security")
                .antMatchers("/swagger-ui.html")
                .antMatchers("/webjars/**")
                .antMatchers("/h2/**");

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.ldapAuthentication()
                .userSearchFilter("uid={0}")
                .contextSource()
                .url(applicationProperties.getLdapUrl())
                .managerDn(applicationProperties.getLdapManagerDn())
                .managerPassword(applicationProperties.getLdapManagerPassword())
                .and()
                .passwordCompare()
                .passwordAttribute("userPassword");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}