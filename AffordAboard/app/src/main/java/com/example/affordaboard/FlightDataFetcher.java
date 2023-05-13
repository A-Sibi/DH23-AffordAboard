package com.example.affordaboard;

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
            .baseUrl("https://mock-flight-data-api.com/")  // replace with the base URL of your API
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    // API interface
    public interface FlightDataApi {
        @GET("flights")
        Call<List<FlightAPIData>> getFlights(@Query("departure_location") String departureLocation,
                                             @Query("destination_location") String destinationLocation,
                                             @Query("departure_date") String departureDate,
                                             @Query("return_date") String returnDate);
        // add more parameters as needed for user preferences
    }

    private FlightDataApi flightDataApi = retrofit.create(FlightDataApi.class);

    public void fetchFlights(String departureLocation, String destinationLocation, String departureDate, String returnDate) {
        Call<List<FlightAPIData>> call = flightDataApi.getFlights(departureLocation, destinationLocation, departureDate, returnDate);
        call.enqueue(new Callback<List<FlightAPIData>>() {
            @Override
            public void onResponse(Call<List<FlightAPIData>> call, Response<List<FlightAPIData>> response) {
                if (!response.isSuccessful()) {
                    // handle the error
                    return;
                }

                List<FlightAPIData> flights = response.body();
                // use this data in your app
            }

            @Override
            public void onFailure(Call<List<FlightAPIData>> call, Throwable t) {
                // handle the failure
            }
        });
    }
}
