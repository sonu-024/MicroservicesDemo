package com.example.user.service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
//        security.authorizeHttpRequests()
//                .anyRequest()
//                .authenticated()
//                .oauth2ResourceServer()
//                .jwt();
//        return security.build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()  // All requests require authentication
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwtConfigurer -> jwtConfigurer
                                .jwkSetUri("https://dev-21471692.okta.com/oauth2/default"))  // Enable OAuth2 JWT authentication
                );

        return http.build();
    }

}
