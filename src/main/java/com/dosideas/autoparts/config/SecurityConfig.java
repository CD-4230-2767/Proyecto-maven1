/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.dosideas.autoparts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


/**
 *
 * @author user
 */
@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        // Este usuario ahora coincide con el correo en tu tabla Cliente
        UserDetails cliente = User.withDefaultPasswordEncoder()
                .username("pruebas@autoparts.com") // <--- CAMBIA ESTO
                .password("123456")
                .roles("CLIENTE")
                .build();

        UserDetails admi67 = User.withDefaultPasswordEncoder()
                .username("Admi67")
                .password("autoparts")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(cliente, admi67);
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/registro", "/css/**", "/img/**").permitAll()

                // CAMBIO AQUÍ: El /** permite acceso a todas las sub-rutas (nuevo, guardar, listar, etc)
                .requestMatchers("/admin/**").hasRole("ADMIN") 

                // Solo los CLIENTES pueden ver el carrito
                .requestMatchers("/carrito/**").hasRole("CLIENTE")

                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .successHandler(successHandler()) // Usamos nuestro manejador personalizado
                .permitAll()
            )
            .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            // Obtenemos los roles del usuario logueado
            boolean isAdmin = authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

            if (isAdmin) {
                response.sendRedirect("/admin/dashboard");
            } else {
                response.sendRedirect("/");
            }
        };
    }
}
