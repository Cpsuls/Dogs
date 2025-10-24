package org.example.doogas.SecurityEventListeners;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

public class AuthenticationEventListener {
    Logger logger= LoggerFactory.getLogger(this.getClass().getName());

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent authenticationSuccessEvent){
        logger.info("success!");
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent ev){
        logger.error("auth failed!");
    }


}
