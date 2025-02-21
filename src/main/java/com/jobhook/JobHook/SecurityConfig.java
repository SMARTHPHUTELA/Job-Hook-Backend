package com.jobhook.JobHook;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain eSecurityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((req)->req.requestMatchers("/**").permitAll().anyRequest().authenticated());
        http.csrf(csrf->csrf.disable());
        return http.build();
    }
}
