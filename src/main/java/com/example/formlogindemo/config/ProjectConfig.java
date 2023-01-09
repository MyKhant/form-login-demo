package com.example.formlogindemo.config;

import com.example.formlogindemo.handler.CustomAuthenticationFailureHandler;
import com.example.formlogindemo.handler.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {
    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Bean
    public UserDetailsService userDetailsService(){
        var uds = new InMemoryUserDetailsManager();
        var user1 = User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();
        var user2 = User.withUsername("mary")
                        .password("12345")
                                .authorities("write")
                                        .build();
        uds.createUser(user2);
        uds.createUser(user1);
        return uds;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Throwable {
        http.formLogin()
                        .successHandler(authenticationSuccessHandler)
                                .failureHandler(authenticationFailureHandler);
//                .defaultSuccessUrl("/hello")
//                .failureUrl("/error1");
//                                        .and()
//                                                .httpBasic();
        http.authorizeHttpRequests()
                .anyRequest()
                .authenticated();
        return http.build();
    }
}
