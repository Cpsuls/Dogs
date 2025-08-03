package org.example.doogas.Factory;

import org.example.doogas.AbstarctClasses.Animal;

public interface AnimalFactory {
    Animal createAnimal(String name, String breed, int size);
}