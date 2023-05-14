package com.example.affordaboard;

import android.util.Log;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class FlightDataFetcher {

    // Retrofit instance
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.aviationstack.com/v1/")  // Base URL ends with '/'
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    // API interface
    public interface FlightDataApi {
        @GET("flights")
        Call<FlightAPIResponse> getFlights(@Query("access_key") String accessKey);
    }

    private FlightDataApi flightDataApi = retrofit.create(FlightDataApi.class);

    // u dokumentaciji pise da imas neke specificne inpute i kao da se i oni zovejo za svaki grad preko neki api, valjda zato je json prazan
    // meni to zvuci komplikovano, mozda probaj da tvoji kod preusmeris na taj amadeus for developers website

    public void fetchFlights(String accessKey, String departureLocation, String destinationLocation, String departureDate, String returnDate) {
        System.out.println("START OF FETCHING PROCESS");
        Call<FlightAPIResponse> call = flightDataApi.getFlights(accessKey);
        call.enqueue(new Callback<FlightAPIResponse>() {
            @Override
            public void onResponse(Call<FlightAPIResponse> call, Response<FlightAPIResponse> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Response Code: " + response.code());
                    return;
                }
                String rawResponse = response.raw().body().toString();
                System.out.println("Raw Response: " + rawResponse);


                FlightAPIResponse apiResponse = response.body();

                // Save the entire JSON to a file
                String json = new Gson().toJson(apiResponse);
                System.out.println(json.toString());
            }

            @Override
            public void onFailure(Call<FlightAPIResponse> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }
}
