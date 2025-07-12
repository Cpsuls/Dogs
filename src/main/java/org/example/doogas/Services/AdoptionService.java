package org.example.doogas.Services;

import org.example.doogas.Model.Dog;
import org.springframework.stereotype.Service;

@Service
public class AdoptionService {
    public String adoptDog(Dog dog){
       return dog.getName()+"\n is adopted!";
    }
}
