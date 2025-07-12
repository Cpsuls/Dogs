package org.example.doogas.Services;


import org.example.doogas.Model.Dog;
import org.example.doogas.Strategies.FeedingContext;
import org.springframework.stereotype.Service;

@Service
public class FeedingService {
    private final FeedingContext feedingContext;

    public FeedingService(FeedingContext feedingContext) {
        this.feedingContext = feedingContext;
    }

    public String feedDog(Dog dog){
        feedingContext.setFeedingStrategy(dog.getSize());
        return feedingContext.feedsDog(dog);

    }
}
