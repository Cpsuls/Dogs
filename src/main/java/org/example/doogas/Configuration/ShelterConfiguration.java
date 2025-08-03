package org.example.doogas.Configuration;

import org.example.doogas.Services.AdoptionService;
import org.example.doogas.Services.FeedingService;
import org.example.doogas.Strategies.FeedingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShelterConfiguration {

//    @Bean
//    public ShelterManager shelterManager() {
//        return ShelterManager.of();
//    }


    @Bean
    public FeedingContext feedingContext() {
        return new FeedingContext();
    }

    @Bean
    public AdoptionService adoptionService() {
        return new AdoptionService();
    }


    @Bean
    public FeedingService feedingService(FeedingContext feedingContext) {
        return new FeedingService(feedingContext);
    }
}