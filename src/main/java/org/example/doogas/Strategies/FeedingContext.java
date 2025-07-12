package org.example.doogas.Strategies;

import org.example.doogas.Model.Dog;
import org.springframework.stereotype.Component;

@Component
public class FeedingContext {
    private FeedingStrategy feedingStrategy;
    public void setFeedingStrategy(int size){
        this.feedingStrategy=switch (size){
            case 1-> new SmallDogFeedingStartegy();
            case 2->new MediumSizeDog();
            default -> new BigDogFeedingStrategy();
        };
    }
    public String feedsDog(Dog dog){
        return feedingStrategy.feed(dog);
    }
}
