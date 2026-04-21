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
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/productlines/**", "/products/**")
                    .hasAnyRole("GOKUL", "ADMIN")
                .requestMatchers("/payments/**")
                    .hasAnyRole("YUVASRI", "ADMIN")
                .requestMatchers("/orders/**", "/orderdetails/**")
                    .hasAnyRole("DHARSHINI", "ADMIN")
                .requestMatchers("/employees/**", "/offices/**", "/customers/**")
                    .hasAnyRole("KEERTHISHA", "ADMIN")
                .requestMatchers(
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic();
        return http.build();
    }
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder encoder) {

        UserDetails gokul = User
                .withUsername("gokul")
                .password(encoder.encode("gokul123"))
                .roles("GOKUL")
                .build();

        UserDetails yuvasri = User
                .withUsername("yuvasri")
                .password(encoder.encode("yuvasri123"))
                .roles("YUVASRI")
                .build();

        UserDetails dharshini = User
                .withUsername("dharshini")
                .password(encoder.encode("dharshini123"))
                .roles("DHARSHINI")
                .build();

        UserDetails keerthisha = User
                .withUsername("keerthisha")
                .password(encoder.encode("keerthisha123"))
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
                admin
        );
    }
}