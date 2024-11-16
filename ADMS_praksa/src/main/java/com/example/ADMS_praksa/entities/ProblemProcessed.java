package com.example.ADMS_praksa.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class ProblemProcessed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int brWorkers;
    private LocalTime predictedTime;
    private LocalDate predictedDate;
    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problemId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    /*@ManyToOne(mappedBy = "problem", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ProblemProcessed> problemProcesseds;*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBrWorkers() {
        return brWorkers;
    }

    public void setBrWorkers(int brWorkers) {
        this.brWorkers = brWorkers;
    }

    public LocalTime getPredictedTime() {
        return predictedTime;
    }

    public void setPredictedTime(LocalTime predictedTime) {
        this.predictedTime = predictedTime;
    }

    public Problem getProblemId() {
        return problemId;
    }

    public void setProblemId(Problem problemId) {
        this.problemId = problemId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public LocalDate getPredictedDate() {
        return predictedDate;
    }

    public void setPredictedDate(LocalDate predictedDate) {
        this.predictedDate = predictedDate;
    }
}
