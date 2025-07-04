package com.chabiamine.Shortify.config;


import org.hibernate.annotations.CollectionTypeRegistration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/Home","/signup","/css/**","/js/**","/Shortify/**").permitAll()
                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/Home")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/Dashboard",true)
                        .permitAll()
                )
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/?logout=success")                );

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
