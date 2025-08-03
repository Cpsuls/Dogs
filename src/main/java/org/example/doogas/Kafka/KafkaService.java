package org.example.doogas.Kafka;

import lombok.RequiredArgsConstructor;
import org.example.doogas.Model.Dog;
import org.example.doogas.Model.SecurityModel.SecutiyRepository.Visitors;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.example.cores.DogEvent;
import java.util.concurrent.ExecutionException;

@Service
public class KafkaService {
    KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    public void sendDogEvent(Dog dog, String id) throws ExecutionException, InterruptedException {
        DogEvent dogEvent = new DogEvent(id, dog.getName(), dog.getBreed(), dog.getSize());
        SendResult<String, Object> result = kafkaTemplate.send("doogas", id, dogEvent).get();
        Visitors visitors=new Visitors();
        LOGGER.info(result.getRecordMetadata().topic());
        LOGGER.info(String.valueOf(result.getRecordMetadata().partition()));
    }


}












//    public void sendfoundDog(Dog dog) throws ExecutionException, InterruptedException {
//        var result=prepareDogForSent(dog,DOG_ADDED);
//        LOGGER.info(result.getRecordMetadata().topic());
//        LOGGER.info(String.valueOf(result.getRecordMetadata().partition()));
//
//    }
//
//    public void sendTakenDog(Dog dog) throws ExecutionException, InterruptedException {
//        var result=prepareDogForSent(dog,DOG_ADDED);
//        LOGGER.info(result.getRecordMetadata().topic());
//        LOGGER.info(String.valueOf(result.getRecordMetadata().partition()));
//
//    }
//
//    public void sendFedDog(Dog dog) throws ExecutionException, InterruptedException {
//        DogEvent dogEvent=new DogEvent(DOG_REMOVED,dog.getName(),dog.getBreed(),dog.getSize());
//        SendResult<String, Object> result=kafkaTemplate.send("doogas",DOG_REMOVED,dogEvent).get();
//        LOGGER.info(result.getRecordMetadata().topic());
//        LOGGER.info(String.valueOf(result.getRecordMetadata().partition()));
//
//
//    }



