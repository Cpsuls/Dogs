package org.example.doogas.Model.SecurityModel.SecutiyRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    private final VisitorsRepository visitorsRepository;


    public CustomUserDetailsService(VisitorsRepository visitorsRepository) {
        this.visitorsRepository = visitorsRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Visitors visitor= visitorsRepository.findVisitorsByUsername(username).
                orElseThrow(()->new UsernameNotFoundException("nf"+username));
        List<GrantedAuthority> authorities= List.of(new SimpleGrantedAuthority(visitor.getRole()));
        logger.info(visitor.getUsername());
        return new User(visitor.getUsername(), visitor.getPassword(),authorities);
    }
}
