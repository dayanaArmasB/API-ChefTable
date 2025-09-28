package com.proyecto.spring_proyecto.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.proyecto.spring_proyecto.application.interfaces.IJwtService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final IJwtService jwtService;

    public SecurityConfig(IJwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // puedes ajustar strength
    }

      @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

      @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {

        JwtAuthorizationFilter jwtAuthorizationFilter = new JwtAuthorizationFilter(authManager, jwtService);

        http
            .csrf(csrf -> csrf.disable())
            .cors(Customizer.withDefaults())
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth

                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                    .requestMatchers(
                            "/api/auth/login",
                            "/api/auth/register"
                    ).permitAll()

                    .anyRequest().authenticated()
            )

            .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // CORS en local (si llamas desde http://localhost:4200, p.ej.)
    @Bean
    CorsConfigurationSource corsConfigurationSource(
            @Value("${cors.allowed-origins:http://localhost:4200}") String allowedOrigins) {
        var cfg = new CorsConfiguration();
        cfg.setAllowedOrigins(List.of(allowedOrigins));
        cfg.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        cfg.setAllowedHeaders(List.of("Content-Type", "Authorization"));
        cfg.setAllowCredentials(true);
        var src = new UrlBasedCorsConfigurationSource();
        src.registerCorsConfiguration("/**", cfg);
        return src;
    }

}
