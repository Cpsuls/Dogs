package org.example.doogas.Configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.doogas.Filters.CsrfFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    DataSource dataSource;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler csrfTokenRequestHandler=
                new CsrfTokenRequestAttributeHandler();
        http.sessionManagement(smc->smc.sessionFixation(sfc->sfc.newSession()).
                invalidSessionUrl("/invalidSession").
                sessionCreationPolicy(SessionCreationPolicy.ALWAYS).
                maximumSessions(1)
                .maxSessionsPreventsLogin(true));
        http.securityContext(contextConfig->contextConfig.requireExplicitSave(false));
        http.cors(corsConfig->corsConfig.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration configuration=new CorsConfiguration();
                configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                configuration.setAllowCredentials(true);
                configuration.setMaxAge(3600L);
                return configuration;
            }
        }));
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
        http.csrf(csrfConfig->csrfConfig.csrfTokenRequestHandler(csrfTokenRequestHandler)
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfFilter(), BasicAuthenticationFilter.class);
//        http.csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
