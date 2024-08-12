package com.example.fillu.sensor.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorDTO {
    @NotEmpty
    @Size(min = 3, max = 30, message = "Имя должно быть больше 3 и меньше 30")
    private String name;
}
