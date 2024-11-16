package com.example.ADMS_praksa.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ProblemProcessedDTO {
    //private long id;
    private int brWorkers;
    private LocalTime predictedTime;
    private LocalDate predictedDate;
    //private long problemId;
    private long userId;

   /* public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }*/

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

    /*public long getProblemId() {
        return problemId;
    }

    public void setProblemId(long problemId) {
        this.problemId = problemId;
    }*/

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getPredictedDate() {
        return predictedDate;
    }

    public void setPredictedDate(LocalDate predictedDate) {
        this.predictedDate = predictedDate;
    }
}
