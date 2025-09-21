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

    /**
     * Configures the security filter chain for the application.
     * @param httpSecurity the HttpSecurity object to configure security settings.
     * @return SecurityFilterChain object that defines the security rules.
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // Configure endpoints and their access rules. If endpoints are not specified, all requests are authenticated by default.
        httpSecurity.authorizeHttpRequests(request ->
                request
//                    .requestMatchers(HttpMethod.GET, "/users").permitAll()
                    .requestMatchers(HttpMethod.POST, "/auth/token", "/auth/introspect").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/users/*").permitAll()
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