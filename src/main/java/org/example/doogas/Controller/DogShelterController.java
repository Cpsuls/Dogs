package org.example.doogas.Controller;

import org.example.doogas.Factory.DogFactory;
import org.example.doogas.Model.*;
import org.example.doogas.Services.AdoptionService;
import org.example.doogas.Services.FeedingService;
import org.example.doogas.State.DogState;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dogshelter")
public class DogShelterController {

    private final AdoptionService adoptionService;
    private final FeedingService feedingService;
    private final ShelterManager shelterManager;
    private final DogFactory dogFactory;

    public DogShelterController(AdoptionService adoptionService,
                                FeedingService feedingService,
                                ShelterManager shelterManager, DogFactory dogFactory) {
        this.adoptionService = adoptionService;
        this.feedingService = feedingService;
        this.shelterManager = shelterManager;
        this.dogFactory = dogFactory;
    }


    @PostMapping("/add")
    public String addDog(@RequestParam String name,
                         @RequestParam String breed,
                         @RequestParam int size) {
        Dog dog = dogFactory.bringDogToShelter(name, breed, size);
        shelterManager.addDog(dog);
        return "Added dog: " + dog.getName() + ". Total dogs: " + shelterManager.getDogCount();
    }

    @GetMapping("/manage")
    public String manageShelter() {
        return shelterManager.manage();
    }


    @PostMapping("/{name}/feed")
    public String feedDog(@PathVariable String name) {
        Dog dog=shelterManager.findDog(name);
        return feedingService.feedDog(dog);
    }

    @PostMapping("/{name}/train")
    public String trainDog(@PathVariable String name) {
        return shelterManager.findDog(name).train();
    }


    @PostMapping("/{name}/run")
    public String makeDogRun(@PathVariable String name) {
        Dog dog=shelterManager.findDog(name);
        dog.runs();
        System.out.println(dog.getDogState());
        System.out.println(dog.getName());
        shelterManager.addDog(dog);
        return "Added dog: " + dog.getName() + ". Total dogs: " + shelterManager.getDogCount();
    }


    @GetMapping("/{name}/state")
    public String checkDogState(@PathVariable String name) {
        Dog dog=shelterManager.findDog(name);
        DogState state =dog.getDogState();
        return "Current state of " + dog.getName() + ":\n" +
                "Play: " + state.play() + "\n" +
                "Feed: " + state.feed() + "\n" +
                "Voice: " + state.voice();
    }


    @PostMapping("/{name}/adopt")
    public String adoptDog(@PathVariable String name) {
        Dog dog=shelterManager.findDog(name);
        shelterManager.minusDog(dog);
        return adoptionService.adoptDog(dog);
    }

}