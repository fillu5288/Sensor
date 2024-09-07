package com.example.fillu.sensor.controllers;

import com.example.fillu.sensor.dto.DataDTO;
import com.example.fillu.sensor.dto.SensorDTO;
import com.example.fillu.sensor.models.Data;
import com.example.fillu.sensor.services.DataService;
import com.example.fillu.sensor.services.SensorService;
import com.example.fillu.sensor.util.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.Valid;
import jdk.jfr.ContentType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/data")
public class DataController {
    private final DataService dataService;
    private final SensorService sensorService;
    private final ModelMapper modelMapper;

    @Autowired
    public DataController(DataService dataService,
                            ModelMapper modelMapper,
                          SensorService sensorService) {
        this.dataService = dataService;
        this.modelMapper = modelMapper;
        this.sensorService = sensorService;
    }

    @GetMapping()
    public List<DataDTO> getData() {
        // статус 200, все ок
        return dataService.findAll()
                .stream()
                .map(this::convertToDataDTO)
                .collect(Collectors.toList()); //джэксон конвертирует в json
    }

    @GetMapping("/rain")
    public Long showRainDays() {return dataService.findAll().stream().filter(Data::isRaining).count();}

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid DataDTO dataDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";"); // делает красивенькое сообщение об ошибке
            }

            throw new SensorNotCreated(errorMsg.toString());
        }

        Data data = convertToData(dataDTO);
        data.setSensor(sensorService.findOneNameIdOrThrow(data.getSensor().getName()));
        dataService.save(data);
        return ResponseEntity.ok(HttpStatus.OK); // отправляет ок
    }

    @ExceptionHandler
    private ResponseEntity<DataErrorResponse> handleException(DataNotFound dataNotFound) {
        DataErrorResponse response = new DataErrorResponse(
                "Data not found",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // статус 404, плохо
    }

    @ExceptionHandler
    private ResponseEntity<DataErrorResponse> handleException(DataNotCreated dataNotCreated) {
        DataErrorResponse response = new DataErrorResponse(
                dataNotCreated.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // статус 404, плохо
    }

    private Data convertToData(@Valid DataDTO dataDTO) {
        return modelMapper.map(dataDTO, Data.class);
    }

    private DataDTO convertToDataDTO(Data data) {
        return modelMapper.map(data, DataDTO.class);
    }
}
