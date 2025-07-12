package org.example.doogas.Strategies;

import org.example.doogas.Model.Dog;

public class BigDogFeedingStrategy implements FeedingStrategy{
    @Override
    public String feed(Dog dog) {
        return "Big dog does eats TOO much"+dog.getName();
    }
}
