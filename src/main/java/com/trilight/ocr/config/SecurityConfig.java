package com.trilight.ocr.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // private final JwtFilter jwtFilter;
    // private final CustomUserDetailsServiceImpl userDetailsService;
    //
    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     return http.csrf(customizer -> customizer.disable())
    //             .authorizeHttpRequests(request -> request
    //                     .requestMatchers("api/user/login", "api/register").permitAll()
    //                     .anyRequest().authenticated())
    //             .formLogin(customizer -> customizer.disable())
    //             .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
    //             .build();
    // }
    //
    // @Bean
    // public AuthenticationProvider authenticationProvider() {
    //     DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    //     provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
    //     provider.setUserDetailsService(userDetailsService);
    //     return provider;
    // }
    //
    // @Bean
    // public BCryptPasswordEncoder bCryptPasswordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }
    //
    // @Bean
    // public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    //     return config.getAuthenticationManager();
    // }
}