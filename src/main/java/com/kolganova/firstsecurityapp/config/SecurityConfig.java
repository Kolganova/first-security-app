package com.kolganova.firstsecurityapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // manage authentication
    @Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }

    // conf SpringSecurity itself + conf authorization
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                //authorization
                .authorizeRequests()
                .requestMatchers("/auth/login", "/error").permitAll()
                .anyRequest().authenticated()
                .and()
                //login
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/hello", true)
                .failureUrl("/auth/login?error");

        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
