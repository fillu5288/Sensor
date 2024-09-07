package com.example.fillu.sensor.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
public class DataDTO {
    @NotNull
    @Min(value = -100, message = "Значение не может быть меньше -100")
    @Max(value = 100, message = "Значение не может быть больше 100")
    @Column(name = "value")
    private Double value;

    @NotNull(message = "Значение или true или false")
    private boolean raining;

    @NotNull(message = "Должен быть указан сенсор!")
    private SensorDTO sensor;
}