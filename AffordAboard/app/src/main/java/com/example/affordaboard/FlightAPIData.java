package com.example.affordaboard;

public class FlightAPIData {
    // Variables that match the JSON field names from the API
    private String destination;
    private String departure;
    private int price;

    // Getters and setters for each variable
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
