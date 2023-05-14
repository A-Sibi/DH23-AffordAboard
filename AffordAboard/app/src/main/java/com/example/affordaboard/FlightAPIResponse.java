package com.example.affordaboard;

import java.util.List;

public class FlightAPIResponse {
    private List<FlightAPIData> flights;

    public List<FlightAPIData> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightAPIData> flights) {
        this.flights = flights;
    }
}
