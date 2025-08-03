package org.example.doogas.Factory;

import org.example.doogas.AbstarctClasses.Animal;
import org.example.doogas.Model.Dog;
import org.springframework.stereotype.Component;

@Component
public class DFactory implements AnimalFactory {
    @Override
    public Animal createAnimal(String name, String breed, int size) {
        return new Dog(name, breed, size);
    }
}