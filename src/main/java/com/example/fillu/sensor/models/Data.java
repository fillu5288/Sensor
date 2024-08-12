package com.example.fillu.sensor.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "data")
public class Data {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;

    @NotNull
    @Min(value = -100, message = "Значение не может быть меньше -100")
    @Max(value = 100, message = "Значение не может быть больше 100")
    @Column(name = "value")
    private Double value;

    @NotNull(message = "Значение или true или false")
    @Column(name = "raining")
    private boolean raining;

    @UpdateTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
