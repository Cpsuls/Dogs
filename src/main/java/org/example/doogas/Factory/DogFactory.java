package org.example.doogas.Factory;


import org.example.doogas.Model.Dog;
import org.springframework.stereotype.Component;
import org.example.doogas.Repository.ShelterRepostitory;

@Component
public class DogFactory{
    private final ShelterRepostitory shelterRepository;

    public DogFactory(ShelterRepostitory shelterRepository) {
        this.shelterRepository = shelterRepository;
    }


    public Dog bringDogToShelter(String name, String breed, int size){
        Dog dog=new Dog(name,breed,size);
        return shelterRepository.save(dog);
    }
}
