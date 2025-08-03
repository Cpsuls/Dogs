package org.example.doogas.Factory;


import org.example.doogas.Model.Dog;
import org.example.doogas.Repository.ShelterRepostitory;
import org.springframework.stereotype.Service;

@Service
public class DogFactoryService {
    private final ShelterRepostitory shelterRepository;
    private final AnimalFactory dogFactory;

    public DogFactoryService(ShelterRepostitory shelterRepository, AnimalFactory dogFactory) {
        this.shelterRepository = shelterRepository;
        this.dogFactory = dogFactory;
    }


    public Dog bringDogToShelter(String name, String breed, int size) {
        Dog dog = (Dog) dogFactory.createAnimal(name, breed, size);
        return shelterRepository.save(dog);
    }
}
