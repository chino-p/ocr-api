package com.trilight.ocr.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http.csrf(csrf -> csrf.disable())
    //             .authorizeHttpRequests(authorizeHttpRequests ->
    //                     authorizeHttpRequests.requestMatchers("/api/login", "/api/register").permitAll()
    //                             .anyRequest().authenticated());
    //
    //     return http.build();
    // }
    //
    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder(); // 使用 BCrypt 对密码加密
    // }
    //
    // @Bean
    // public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    //     return config.getAuthenticationManager();
    // }
}