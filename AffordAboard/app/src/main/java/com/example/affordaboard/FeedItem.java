package com.example.affordaboard;

public class FeedItem {
    private String userName;
    private String travelLocation;
    private String travelDates;

    // Constructor, getters and setters
    public FeedItem(String userName, String travelLocation, String travelDates){
        this.userName = userName;
        this.travelLocation = travelLocation;
        this.travelDates = travelDates;
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
}