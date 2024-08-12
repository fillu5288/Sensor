package com.example.fillu.sensor.repositories;

import com.example.fillu.sensor.models.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface DataRepository extends JpaRepository<Data, Integer> { }
