package org.example.doogas.Strategies;


import org.example.doogas.Model.Dog;

public class SmallDogFeedingStartegy implements FeedingStrategy {
    @Override
    public String feed(Dog dog) {
        return "Little dog does not eat much"+dog.getName();

    }
}
