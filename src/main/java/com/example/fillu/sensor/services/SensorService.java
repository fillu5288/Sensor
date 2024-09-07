package com.example.fillu.sensor.services;

import com.example.fillu.sensor.models.Sensor;
import com.example.fillu.sensor.repositories.SensorRepository;
import com.example.fillu.sensor.util.SensorNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    public Optional<Sensor> findOneName(String name) {return sensorRepository.findByName(name);}

    public Sensor findOneByName(String name) {
        Optional<Sensor> foundSensor = sensorRepository.findByName(name);
        return foundSensor.orElseThrow(SensorNotFound::new);
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    public Sensor findOneNameIdOrThrow(String name) {
        return sensorRepository.findByName(name).orElseThrow(SensorNotFound::new);
    }
}
