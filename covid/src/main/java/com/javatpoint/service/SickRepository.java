package com.javatpoint.service;

import com.javatpoint.model.Sick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public interface SickRepository extends JpaRepository<Sick,Long> {
    List<Sick> findAllByNegativeAfter(LocalDate negativeAfter);
    List<Sick> findAllByNegativeBefore(LocalDate negativeBefore);
    List<Sick> findAllByPositiveAfter(LocalDate positiveAfter);
    List<Sick> findAllByPositiveBefore(LocalDate positiveBefore);
    Sick findByMemberId(long memberId);
    Sick findAllByPositiveAfterAndNegativeBefore(LocalDate positiveAfter,LocalDate negative);

}
