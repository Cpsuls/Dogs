package org.example.doogas.Model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.doogas.State.DogState;
import org.example.doogas.State.HappyState;
import org.example.doogas.State.SleepyState;

@Converter
public class DogStateConverter implements AttributeConverter<DogState, String> {

    @Override
    public String convertToDatabaseColumn(DogState state) {
        if (state instanceof HappyState) return "HAPPY";
        if (state instanceof SleepyState) return "SLEEPY";
        throw new IllegalArgumentException("Unknown state type");
    }

    @Override
    public DogState convertToEntityAttribute(String dbData) {
        return switch (dbData) {
            case "HAPPY" -> new HappyState();
            case "SLEEPY" -> new SleepyState();
            default -> throw new IllegalArgumentException("Unknown state value");
        };
    }
}