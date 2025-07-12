package org.example.doogas.Repository;


import org.example.doogas.Model.Dog;
import org.example.doogas.State.DogState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterRepostitory extends JpaRepository<Dog,Long> {
    Dog findByName(String name);



}
