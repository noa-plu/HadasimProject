package com.javatpoint.service;

import com.javatpoint.model.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Component
public interface VaccinationRepository extends JpaRepository<Vaccination,Long>{
    Vaccination findById(long id);
    List<Vaccination> findAllByGettingBefore(LocalDate date);
    List<Vaccination> findAllByGettingAfter(LocalDate date);
    List<Vaccination> findAllBySickId(long sickId);
    List<Vaccination> findAllByManufacturer(String manufacturer);
    Vaccination findByGettingAndSickId(LocalDate date,long sickId);
}