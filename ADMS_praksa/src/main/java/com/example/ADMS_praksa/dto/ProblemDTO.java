package com.example.ADMS_praksa.dto;

import com.example.ADMS_praksa.entities.ProblemType;

import java.time.LocalDate;
import java.time.LocalTime;

public class ProblemDTO {
    private long id;
    private String place;
    private String street;
    private int number;
    private LocalDate creationDate;
    private LocalTime creationTime;
    private ProblemType problemType;
    private int electirityMeter;
    private String status;
    private String comment;
    private int brWorkers;
    private LocalTime predictedTime;
    private LocalDate predictedDate;
    //private long userId;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProblemType getProblemType() {
        return problemType;
    }

    public void setProblemType(ProblemType problemType) {
        this.problemType = problemType;
    }

    public int getElectirityMeter() {
        return electirityMeter;
    }

    public void setElectirityMeter(int electirityMeter) {
        this.electirityMeter = electirityMeter;
    }

    public LocalTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalTime creationTime) {
        this.creationTime = creationTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public LocalDate getPredictedDate() {
        return predictedDate;
    }

    public void setPredictedDate(LocalDate predictedDate) {
        this.predictedDate = predictedDate;
    }

   /* public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }*/
}
