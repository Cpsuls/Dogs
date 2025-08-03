package org.example.doogas.Kafka;

public enum DogTypeEvents {
    ADDED("dog-added"),
    FED("dog-fed"),
    ADOPTED("dog-adopted"),
    RUN("dog-run"),
    TRAIN("dog-train"),
    STATE("dog-check");

    private final String topicKey;

    DogTypeEvents(String topicKey) {
        this.topicKey = topicKey;
    }

    public String getEventName() {
        return topicKey;
    }
}
