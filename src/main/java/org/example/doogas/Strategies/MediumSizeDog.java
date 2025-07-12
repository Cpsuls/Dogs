package org.example.doogas.Strategies;

import org.example.doogas.Model.Dog;

public class MediumSizeDog implements FeedingStrategy{
    @Override
    public String feed(Dog dog) {
        return "Medium dog does not eat too much\t"+dog.getName();
    }
}
