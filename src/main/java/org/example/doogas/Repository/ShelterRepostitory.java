package org.example.doogas.Repository;


import org.example.doogas.Model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterRepostitory extends JpaRepository<Dog,Long> {
    Dog findByName(String name);



}
