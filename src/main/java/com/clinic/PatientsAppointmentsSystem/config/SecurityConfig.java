package com.clinic.PatientsAppointmentsSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/", "/home", "/contact", "/about", "/login","/signup","/processLogin","/saveUser", "/resources/**", "/assets/**","/h2-console","/saveMsg","/saveAppointment","/message-details")
                        .ignoringRequestMatchers("/h2-console/**","/saveUser","/signup") // Disable CSRF for H2 console
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/", "/home", "/contact", "/about", "/login","/signup","/processLogin","/saveUser", "/resources/**", "/assets/**","/h2-console","/saveMsg","/saveAppointment").permitAll()
                        .requestMatchers("/dashboard").authenticated()  // Protected page
                        .anyRequest().authenticated()  // All other endpoints require authentication
                )
                .formLogin(loginConfigurer-> loginConfigurer
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard")
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logoutConfigurer -> logoutConfigurer
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .permitAll()
                )
                .httpBasic(withDefaults())
                .headers(headers ->
                        headers
                                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                ).sessionManagement(sessionManagement ->
                sessionManagement
                        .invalidSessionUrl("/login")  // Redirect to this URL on invalid session
                        .maximumSessions(1)  // Limit to one session per user
                        .expiredUrl("/login")  // Redirect to this URL when session expires
        );
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


}
