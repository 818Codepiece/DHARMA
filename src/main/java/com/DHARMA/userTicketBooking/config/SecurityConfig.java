package com.DHARMA.userTicketBooking.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // H2 console requires this
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // allow frames for H2
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // allow H2
                        .requestMatchers("/", "/home", "/login").permitAll() // allow basic pages
                        .anyRequest().authenticated() // secure all others
                )
                .formLogin(form -> form
                        .loginPage("/login") // your login page
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }
}
