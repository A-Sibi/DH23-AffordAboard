package com.example.affordaboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class newAdventure extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_adventure);
    }

    // TODO: those are only error prevention placeholders, insert real data from SharedPreferences!
    String departureLocation;
    String destinationLocation;
    String departureDate;
    String returnDate;


    public void letsGo(View view) {
        //TODO: fetch airplane ticket
        FlightDataFetcher flightDataFetcher = new FlightDataFetcher();
        flightDataFetcher.fetchFlights(departureLocation, destinationLocation, departureDate, returnDate);
    }

    public void back(View view) {
        //TODO
    }
}