package com.example.ADMS_praksa.repositories;


import com.example.ADMS_praksa.entities.Problem;
import com.example.ADMS_praksa.entities.ProblemProcessed;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProblemProcessedRepository extends JpaRepository<ProblemProcessed, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM ProblemProcessed p WHERE p.problemId = :problemId")
    void deleteByProblemId(Problem problemId);
    List<ProblemProcessed> findByProblemId_PlaceAndProblemId_StreetAndProblemId_Number(String place, String street, int number);
    void deleteByProblemIdIn(List<Problem> problems);
}
