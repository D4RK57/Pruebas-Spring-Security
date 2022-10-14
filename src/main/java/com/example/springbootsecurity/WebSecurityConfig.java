package com.example.springbootsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Activa spring security
public class WebSecurityConfig {
    // Se asegura de que solo usuarios autenticados puedan ver el contenido de hello

    // Este método define cuáles URL están restringidas y cuáles no.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        // No requieren autenticación ( / y home)
                        .antMatchers("/", "/inicio").permitAll()
                        .anyRequest().authenticated()
                )
                // Página de login customizada
                .formLogin((form) -> form
                        // permite acceso a todos
                        .loginPage("/iniciar-sesion")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    // Este método configura un almacén de usuarios en memoria con un solo usuario.
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}
