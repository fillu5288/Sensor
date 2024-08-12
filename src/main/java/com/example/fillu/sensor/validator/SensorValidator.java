package com.example.fillu.sensor.validator;

import com.example.fillu.sensor.models.Sensor;
import com.example.fillu.sensor.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@SuppressWarnings("NullableProblems")
@Component
public class SensorValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Sensor sensor = (Sensor) obj;

        if (sensorService.findOneName(sensor.getName()).isPresent()) {
            errors.rejectValue("name", "", "Sensor is est");
        }
    }
}
