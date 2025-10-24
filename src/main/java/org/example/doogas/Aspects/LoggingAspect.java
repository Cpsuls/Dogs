package org.example.doogas.Aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger LOGGER= LoggerFactory.getLogger(this.getClass().getName());

    @Pointcut("within(org.example.doogas.*)")
    public void any(){
    }

    @Before("within(org.example.doogas.*)")
    public void before(JoinPoint joinPoint) {
        LOGGER.info("Entering " + joinPoint.getSignature().getName());
    }
}
