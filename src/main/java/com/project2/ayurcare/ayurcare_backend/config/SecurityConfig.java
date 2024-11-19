//package com.project2.ayurcare.ayurcare_backend.config;
//
//import com.project2.ayurcare.ayurcare_backend.Service.MedicalUserDetailsImpl;
//import com.project2.ayurcare.ayurcare_backend.exceptions.CustomAccessDeniedhandler;
//import com.project2.ayurcare.ayurcare_backend.filter.JwtAuthenticationFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.HttpStatusEntryPoint;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final MedicalUserDetailsImpl medicalUserDetails;
//    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//    private final CustomAccessDeniedhandler accessDeniedhandler;
//    private final CustomLogoutHandler logoutHandler;
//
//    public SecurityConfig(MedicalUserDetailsImpl medicalUserDetails, JwtAuthenticationFilter jwtAuthenticationFilter, CustomAccessDeniedhandler accessDeniedhandler, CustomLogoutHandler logoutHandler) {
//        this.medicalUserDetails = medicalUserDetails;
//        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//        this.accessDeniedhandler = accessDeniedhandler;
//        this.logoutHandler = logoutHandler;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(
//                        req -> req.requestMatchers("/login/**", "/register/**")
//                                .permitAll()
//                                .requestMatchers("admin_only/**").hasAuthority("ADMIN")
//                                .anyRequest()
//                                .authenticated()
//                ).userDetailsService(medicalUserDetails)
//                .exceptionHandling(e -> e.accessDeniedHandler(accessDeniedhandler)
//                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .logout(l->l.logoutUrl("/logout")
//                        .addLogoutHandler(logoutHandler)
//                        .logoutSuccessHandler(
//                                ((request, response, authentication) -> SecurityContextHolder.clearContext())
//                        ))
//                .build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }
//
//}


package com.project2.ayurcare.ayurcare_backend.config;

import com.project2.ayurcare.ayurcare_backend.Service.MedicalUserDetailsImpl;
import com.project2.ayurcare.ayurcare_backend.exceptions.CustomAccessDeniedhandler;
import com.project2.ayurcare.ayurcare_backend.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final MedicalUserDetailsImpl medicalUserDetails;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomAccessDeniedhandler accessDeniedhandler;
    private final CustomLogoutHandler logoutHandler;

    public SecurityConfig(MedicalUserDetailsImpl medicalUserDetails, JwtAuthenticationFilter jwtAuthenticationFilter, CustomAccessDeniedhandler accessDeniedhandler, CustomLogoutHandler logoutHandler) {
        this.medicalUserDetails = medicalUserDetails;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.accessDeniedhandler = accessDeniedhandler;
        this.logoutHandler = logoutHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req -> req
                                .requestMatchers("/login/**", "/register/**", "/medicine/**").permitAll()
                                .requestMatchers("/admin_only/**").hasAuthority("ADMIN")
                                .anyRequest().authenticated()
                ).userDetailsService(medicalUserDetails)
                .exceptionHandling(e -> e.accessDeniedHandler(accessDeniedhandler)
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(l -> l.logoutUrl("/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler(
                                ((request, response, authentication) -> SecurityContextHolder.clearContext())
                        ))
                .build();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(
//                        req -> req
//                                .requestMatchers("/login/**", "/register/**").permitAll()
//                                .requestMatchers("/admin_only/**").hasAuthority("ADMIN")
//                                .requestMatchers("/users/**").hasAnyAuthority("USER", "ADMIN")
//                                .anyRequest().denyAll()
//                ).userDetailsService(medicalUserDetails)
//                .exceptionHandling(e -> e.accessDeniedHandler(accessDeniedhandler)
//                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .logout(l -> l.logoutUrl("/logout")
//                        .addLogoutHandler(logoutHandler)
//                        .logoutSuccessHandler(
//                                ((request, response, authentication) -> SecurityContextHolder.clearContext())
//                        ))
//                .build();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
