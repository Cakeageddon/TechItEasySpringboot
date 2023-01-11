package com.voorbeeld.TechItEasy.config;

import com.voorbeeld.TechItEasy.filter.JwtRequestFilter;
import com.voorbeeld.TechItEasy.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    CustomUserDetailsService customUserDetailsService;
    JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        InMemoryUserDetailsManager man = new InMemoryUserDetailsManager();
        UserDetails u1 = User
                .withUsername("user")
                .password(encoder.encode("user"))
                .roles("USER")
                .build();
        man.createUser(u1);

        UserDetails u2 = User
                .withUsername("admin")
                .password(encoder.encode("admin"))
                .roles("ADMIN")
                .build();
        man.createUser(u2);

        return man;
    }

    public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    //    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .httpBasic()
//                .and()
//                .authorizeHttpRequests()
//                .antMatchers(HttpMethod.GET, "/**") .hasAnyRole("USER","ADMIN")
//                .antMatchers(HttpMethod.POST,"/**").hasRole("ADMIN")
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        return http.build();
//    }
@Bean
protected SecurityFilterChain filter (HttpSecurity http) throws Exception {

    //JWT token authentication
    http
            .csrf().disable()
            .httpBasic().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/users").permitAll()
            .antMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
            .antMatchers(HttpMethod.POST,"/users/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
            /*voeg de antmatchers toe voor admin(post en delete) en user (overige)*/
            .antMatchers("/authenticated").authenticated()
            .antMatchers("/authenticate").permitAll()/*allen dit punt mag toegankelijk zijn voor niet ingelogde gebruikers*/
            .anyRequest().permitAll()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
}
}
