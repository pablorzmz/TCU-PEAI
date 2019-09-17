package com.paei.springboot.backend.apirest.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig  extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // Se van dando reglas para acceder
                // .antMatchers(HttpMethod.GET,"/api/test").permitAll()
                .antMatchers("/api/**").permitAll()
                // Todas las demás requieren autorización
                .anyRequest().authenticated()
        ;
    }
}
