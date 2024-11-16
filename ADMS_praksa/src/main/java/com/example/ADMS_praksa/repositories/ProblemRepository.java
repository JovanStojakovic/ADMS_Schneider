package com.example.ADMS_praksa.repositories;

import com.example.ADMS_praksa.entities.Problem;
import com.example.ADMS_praksa.entities.ProblemType;
import com.example.ADMS_praksa.entities.Status;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
    List<Problem> findByStatus(Status status);
    List<Problem> findByPlaceAndStreetAndNumberAndStatus(String place, String street, int number, Status status);
    List<Problem> findByPlaceAndStreetAndNumber(String place, String street, int number);

    @Query("SELECT p FROM Problem p WHERE " +
            "(:place IS NULL OR p.place = :place) AND " +
            "(:street IS NULL OR p.street = :street) AND " +
            "(:number IS NULL OR p.number = :number) AND " +
            "(:status IS NULL OR p.status = :status) AND " +
            "(:problemType IS NULL OR p.problemType = :problemType)")
    List<Problem> findByFilters(@Param("place") String place,
                                @Param("street") String street,
                                @Param("number") Integer number,
                                @Param("status") Status status,
                                @Param("problemType") ProblemType type,
                                Sort sort);

}
