package org.example.doogas.Model.SecurityModel.SecutiyRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PwdAuthenticationProvider implements AuthenticationProvider {
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    public PwdAuthenticationProvider(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username=authentication.getName();
        String password=authentication.getCredentials().toString();
        UserDetails userDetails=customUserDetailsService.loadUserByUsername(username);
        logger.info(userDetails.getUsername());
        logger.info(userDetails.getPassword());
        logger.info(password);
        logger.info(username);
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            logger.info("Successfully authenticated user " + username);
            return new UsernamePasswordAuthenticationToken(username,password,
                    userDetails.getAuthorities());
        } else {
            throw  new BadCredentialsException("Invalid password");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}

