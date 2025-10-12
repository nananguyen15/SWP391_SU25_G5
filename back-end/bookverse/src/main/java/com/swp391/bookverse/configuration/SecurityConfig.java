package com.swp391.bookverse.configuration;

import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @Author huangdat
 */

/**
 * Security configuration for the application.
 * This class configures security settings, including JWT authentication.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // JWT signing key, used to sign and verify JWT tokens.
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    // Define endpoint access rules based on user roles and HTTP methods
    private final String[] PUBCLIC_POST_ENDPOINTS = {"/auth/token", "/auth/introspect", "/users"};
    private final String[] PUBLIC_GET_ENDPOINTS = {""};

    private final String[] ADMIN_GET_ENDPOINTS = {"/users"};
    private final String[] ADMIN_POST_ENDPOINTS = {""};
    private final String[] ADMIN_PUT_ENDPOINTS = {""};
    private final String[] ADMIN_DELETE_ENDPOINTS = {""};

    private final String[] STAFF_GET_ENDPOINTS = {""};
    private final String[] STAFF_POST_ENDPOINTS = {""};
    private final String[] STAFF_PUT_ENDPOINTS = {""};
    private final String[] STAFF_DELETE_ENDPOINTS = {""};


    /**
     * Configures the security filter chain for the application.
     *
     * @param httpSecurity the HttpSecurity object to configure security settings.
     * @return SecurityFilterChain object that defines the security rules.
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        // Define authorization rules for different endpoints and HTTP methods based on user roles and scopes in JWT
        httpSecurity.authorizeHttpRequests(request ->
                request
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/docs"              // because springdoc.swagger-ui.path = /docs
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, PUBCLIC_POST_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.GET, ADMIN_GET_ENDPOINTS).hasAnyAuthority("SCOPE_ADMIN")
                        .anyRequest().authenticated());

        // Configure ability to use form login and basic authentication
        httpSecurity.oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder()))
        );

        // Disable CSRF protection for simplicity in this example.
        httpSecurity.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());

        return httpSecurity.build();
    }

    /**
     * Creates a JwtDecoder bean that uses a secret key to decode JWT tokens.
     *
     * @return JwtDecoder configured with the secret key and HS512 algorithm.
     */
    @Bean
    JwtDecoder jwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SIGNER_KEY.getBytes(StandardCharsets.UTF_8), "HS512");
        return NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}