package org.example.doogas.Controller;

import lombok.RequiredArgsConstructor;
import org.example.doogas.Factory.DogFactoryService;
import org.example.doogas.Kafka.DogTypeEvents;
import org.example.doogas.Kafka.KafkaService;
import org.example.doogas.Model.*;
import org.example.doogas.Services.AdoptionService;
import org.example.doogas.Services.FeedingService;
import org.example.doogas.State.DogState;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/dogshelter")
public class DogShelterController {

    private final AdoptionService adoptionService;
    private final FeedingService feedingService;
    private final ShelterManager shelterManager;
    private final DogFactoryService dogFactory;
    private final KafkaService kafkaService;

    public DogShelterController(AdoptionService adoptionService,
                                FeedingService feedingService,
                                ShelterManager shelterManager, DogFactoryService dogFactory,
                                KafkaService kafkaService) {
        this.adoptionService = adoptionService;
        this.feedingService = feedingService;
        this.shelterManager = shelterManager;
        this.dogFactory = dogFactory;
        this.kafkaService=kafkaService;
    }


    @PostMapping("/add")
    public String addDog(@RequestParam String name,
                         @RequestParam String breed,
                         @RequestParam int size)
            throws ExecutionException, InterruptedException {
        Dog dog = dogFactory.bringDogToShelter(name, breed, size);
        shelterManager.addDog(dog);
        kafkaService.sendDogEvent(dog, DogTypeEvents.ADDED.getEventName());
        return "Added dog: " + dog.getName() + ". Total dogs: " + shelterManager.getDogCount();
    }

    @GetMapping("/manage")
    public String manageShelter() {
        return shelterManager.manage();
    }


    @PostMapping("/{name}/feed")
    public String feedDog(@PathVariable String name) throws ExecutionException, InterruptedException {
        Dog dog=shelterManager.findDog(name);
        kafkaService.sendDogEvent(dog,DogTypeEvents.FED.getEventName());
        return feedingService.feedDog(dog);
    }

    @PostMapping("/{name}/train")
    public String trainDog(@PathVariable String name) throws ExecutionException, InterruptedException {
        Dog dog=shelterManager.findDog(name);
        kafkaService.sendDogEvent(dog,DogTypeEvents.TRAIN.getEventName());
        return shelterManager.findDog(name).train();
    }


    @PostMapping("/{name}/run")
    public String makeDogRun(@PathVariable String name) throws ExecutionException, InterruptedException {
        Dog dog=shelterManager.findDog(name);
        dog.runs();
        shelterManager.addDog(dog);
        kafkaService.sendDogEvent(dog,DogTypeEvents.RUN.getEventName());
        return "Dog runs: " + dog.getName() + ". Total dogs: " + shelterManager.getDogCount();
    }


    @GetMapping("/{name}/state")
    public String checkDogState(@PathVariable String name) throws ExecutionException, InterruptedException {
        Dog dog=shelterManager.findDog(name);
        DogState state =dog.getDogState();
        kafkaService.sendDogEvent(dog,DogTypeEvents.STATE.getEventName());
        return "Current state of " + dog.getName() + ":\n" +
                "Play: " + state.play() + "\n" +
                "Feed: " + state.feed() + "\n" +
                "Voice: " + state.voice();
    }


    @PostMapping("/{name}/adopt")
    public String adoptDog(@PathVariable String name) throws ExecutionException, InterruptedException {
        Dog dog=shelterManager.findDog(name);
        kafkaService.sendDogEvent(dog,DogTypeEvents.ADOPTED.getEventName());
        shelterManager.minusDog(dog);
        return adoptionService.adoptDog(dog);
    }

}