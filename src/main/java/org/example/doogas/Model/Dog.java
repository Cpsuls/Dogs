package org.example.doogas.Model;

import jakarta.persistence.*;
import org.example.doogas.AbstarctClasses.Animal;
import org.example.doogas.Interfacees.Likeable;
import org.example.doogas.Interfacees.Trainable;
import org.example.doogas.Interfacees.Feedable;
import org.example.doogas.State.DogState;
import org.example.doogas.State.HappyState;
import org.example.doogas.State.SleepyState;


@Entity
@Table(name = "dogs")
public class Dog extends Animal implements Trainable,Feedable, Likeable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name,breed;
    private int size;
    @Transient
    private boolean isBig;
    @Column(name = "current_state")
    @Convert(converter = DogStateConverter.class)
    private  DogState dogState;

    public Dog() {

    }

    public DogState getDogState() {
        return dogState;
    }

    public String getBreed() {
        return breed;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public Dog(String name, String breed, int size) {
        this.name = name;
        this.breed = breed;
        this.size = size;
        if(size>1) isBig=true;
        dogState=new HappyState();
    }

    public  void pet(){
        dogState=new HappyState();
    }

    public  void runs(){
        dogState=new SleepyState();
    }

    @Override
    public String feed() {
        return name+"is eating\n"+"What a nice"+ breed;
    }

    @Override
    public String train() {
        return name+"\n is training";
    }

    @Override
    public String woof() {
        if(isBig){
            return "WOOOF!";
        }
        return "woof";
    }

    @Override
    public String like() {
        return "Lovely Dog!";
    }
}
