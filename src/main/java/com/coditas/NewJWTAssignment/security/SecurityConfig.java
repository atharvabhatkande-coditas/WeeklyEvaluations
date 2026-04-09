package com.coditas.NewJWTAssignment.security;

import com.coditas.NewJWTAssignment.filter.JwtFilter;
import com.coditas.NewJWTAssignment.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){

        http.csrf(csrf->csrf.disable());
        return http.authorizeHttpRequests(auth->{
                    auth.requestMatchers(HttpMethod.POST,"/auth/register").permitAll();
                    auth.requestMatchers(HttpMethod.POST,"/auth/login").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/api/user/**").hasRole("USER");
                    auth.requestMatchers(HttpMethod.GET,"/api/admin/**").hasRole("ADMIN");

                    auth.anyRequest().authenticated();
                http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        })
         .build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManage(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder){

        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(daoAuthenticationProvider);

    }
}
