package com.example.fillu.sensor.services;

import com.example.fillu.sensor.models.Data;
import com.example.fillu.sensor.repositories.DataRepository;
import com.example.fillu.sensor.util.DataNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class DataService {
    private final DataRepository dataRepository;
    private final SensorService sensorService;

    @Autowired
    public DataService(DataRepository dataRepository,
                       SensorService sensorService) {
        this.dataRepository = dataRepository;
        this.sensorService = sensorService;
    }

    public List<Data> findAll() {
        return dataRepository.findAll();
    }

    public Data findOneById(int id) {
        Optional<Data> foundData = dataRepository.findById(id);
        return foundData.orElseThrow(DataNotFound::new);
    }

    @Transactional
    public void save(Data data) {
        data.setCreatedAt(LocalDateTime.now());
        dataRepository.save(data);
    }
}
