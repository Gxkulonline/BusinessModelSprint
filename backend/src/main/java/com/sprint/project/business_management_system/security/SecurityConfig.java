package com.sprint.project.business_management_system.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/me").authenticated()
                        .requestMatchers("/productlines/**", "/payments/**")
                        .hasAnyRole("KEERTHISHA", "ADMIN")
                        .requestMatchers("/products/**", "/customers/**")
                        .hasAnyRole("YUVASRI", "ADMIN")
                        .requestMatchers("/orders/**", "/orderdetails/**")
                        .hasAnyRole("DHARSHINI", "ADMIN")
                        .requestMatchers("/employees/**", "/offices/**", "/api/reports/**")
                        .hasAnyRole("GOKUL", "ADMIN")
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html")
                        .permitAll()
                        .anyRequest().authenticated())
                .httpBasic();
        return http.build();
    }

    @Bean
    public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {
        org.springframework.web.cors.CorsConfiguration configuration = new org.springframework.web.cors.CorsConfiguration();
        configuration.setAllowedOrigins(java.util.List.of("http://localhost:4200"));
        configuration.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(java.util.List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder encoder) {

        UserDetails gokul = User
                .withUsername("gokul")
                .password(encoder.encode("1234"))
                .roles("GOKUL")
                .build();

        UserDetails yuvasri = User
                .withUsername("yuvasri")
                .password(encoder.encode("1234"))
                .roles("YUVASRI")
                .build();

        UserDetails dharshini = User
                .withUsername("dharshini")
                .password(encoder.encode("1234"))
                .roles("DHARSHINI")
                .build();

        UserDetails keerthisha = User
                .withUsername("keerthisha")
                .password(encoder.encode("1234"))
                .roles("KEERTHISHA")
                .build();

        UserDetails admin = User
                .withUsername("admin")
                .password(encoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(
                gokul,
                yuvasri,
                dharshini,
                keerthisha,
                admin);
    }
}