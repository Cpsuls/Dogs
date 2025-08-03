package org.example.doogas.Configuration;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    DataSource dataSource;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((request-> request
                .requestMatchers("/dogshelter/add").authenticated()
                .requestMatchers("/dogshelter/manage").permitAll()
                .requestMatchers("/dogshelter/*/feed").permitAll()
                .requestMatchers("/dogshelter/*/train").authenticated()
                .requestMatchers("/dogshelter/*/adopt").authenticated()
                .requestMatchers("/dogshelter/*/run").authenticated()
                .requestMatchers("/dogshelter/*/state").authenticated()
        ));
        http.formLogin(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(smc->smc.invalidSessionUrl("/invalidSession").
                maximumSessions(1).maxSessionsPreventsLogin(true));
        http.httpBasic(Customizer.withDefaults());
        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
