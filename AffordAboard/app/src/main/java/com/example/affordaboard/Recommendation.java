package com.example.affordaboard;

import java.util.UUID;

public class Recommendation {
    private String id;
    private String userName;
    private String travelLocation;
    private String travelDates;
    private String numOfPeople;
    private String numOfMula;

    // Constructor, getters and setters
    public Recommendation(String userName, String travelLocation, String travelDates, String numOfPeople, String numOfMula) {
        this.id = UUID.randomUUID().toString();
        this.userName = userName;
        this.travelLocation = travelLocation;
        this.travelDates = travelDates;
        this.numOfPeople = numOfPeople;
        this.numOfMula = numOfMula;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTravelLocation() {
        return travelLocation;
    }

    public void setTravelLocation(String travelLocation) {
        this.travelLocation = travelLocation;
    }

    public String getTravelDates() {
        return travelDates;
    }

    public void setTravelDates(String travelDates) {
        this.travelDates = travelDates;
    }

    public String getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(String numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public String getNumOfMula() {
        return numOfMula;
    }

    public void setNumOfMula(String numOfMula) {
        this.numOfMula = numOfMula;
    }

    public String getId() {
        return id;
    }
}
