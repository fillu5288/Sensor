package com.example.fillu.sensor.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "sensor")
public class Sensor implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Size(min = 3, max = 30, message = "Имя должно быть больше 3 и меньше 30")
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "sensor")
    private List<Data> data;
}
