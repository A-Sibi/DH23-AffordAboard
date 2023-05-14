package com.example.affordaboard;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class AddJourneyDialogFragment extends DialogFragment implements API.APICallback{

    private EditText whereFromEditText;
    private EditText whereToEditText;
    private EditText whenFromEditText;
    private EditText whenToEditText;
    private EditText numOfPeopleEditText;
    private EditText numOfMulaEditText;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_journey, null);

        whereFromEditText = view.findViewById(R.id.whereFromEditText);
        whereToEditText = view.findViewById(R.id.whereToEditText);
        whenFromEditText = view.findViewById(R.id.whenFromEditText);
        whenToEditText = view.findViewById(R.id.whenToEditText);

        numOfPeopleEditText = view.findViewById(R.id.numOfPeopleEditText);
        numOfMulaEditText = view.findViewById(R.id.numOfMulaEditText);


        // Adding a new journey
        builder.setView(view)
                .setPositiveButton("Submit", (dialog, id) -> {
                    String travelLocation = whereFromEditText.getText().toString() + " - " + whereToEditText.getText().toString();
                    String travelDates = whenFromEditText.getText().toString() + " - " + whenToEditText.getText().toString();
                    String numberOfPeople = numOfPeopleEditText.getText().toString();
                    String young_mula_baby = numOfMulaEditText.getText().toString();

                    FeedActivity activity = (FeedActivity) getActivity();
                    activity.addNewJourney(travelLocation, travelDates, numberOfPeople, young_mula_baby);

                    try {

                        HashMap<String, Integer> affordable = new HashMap<String, Integer>();
                        API.APICallTask api = new API.APICallTask(whereFromEditText.getText().toString().substring(0,3).toUpperCase(),
                                whereToEditText.getText().toString().substring(0,3).toUpperCase(), whenFromEditText.getText().toString(),
                                whenToEditText.getText().toString(), Integer.parseInt(numberOfPeople), Integer.parseInt(young_mula_baby), this);

                        String jsonString = api.execute().get();
                        System.out.println("PHASE 1");


                        ArrayList<JsonDataObject> allFlights = new ArrayList<>();
                        // TODO: perform jsonString parsing
                        try {
                            // Create a JSON object from the JSON string
                            JSONObject jsonObject = new JSONObject(jsonString);

                            // Get the data array from the JSON object
                            JSONArray dataArray = jsonObject.getJSONArray("data");

                            System.out.println("PHASE 2");

                            // Iterate over the data array
                            for (int i = 0; i < dataArray.length(); i++) {
                                // Get the flight-offer object at the current index
                                JSONObject flightOffer = dataArray.getJSONObject(i);

                                // Get the price object from the flight-offer object
                                JSONObject priceObject = flightOffer.getJSONObject("price");

                                // Get the total price from the price object
                                String totalPrice = priceObject.getString("total");

                                // Get the itineraries array from the flight-offer object
                                JSONArray itinerariesArray = flightOffer.getJSONArray("itineraries");

                                // Get the first itinerary from the itineraries array
                                JSONObject firstItinerary = itinerariesArray.getJSONObject(0);

                                // Get the segments array from the first itinerary
                                JSONArray segmentsArray = firstItinerary.getJSONArray("segments");

                                // Get the first segment from the segments array
                                JSONObject firstSegment = segmentsArray.getJSONObject(0);

                                // Get the departure object from the first segment
                                JSONObject departureObject = firstSegment.getJSONObject("departure");

                                // Get the departure time from the departure object
                                String departureAt = departureObject.getString("at");

                                // Get the arrival object from the first segment
                                JSONObject arrivalObject = firstSegment.getJSONObject("arrival");

                                // Get the arrival time from the arrival object
                                String arrivalAt = arrivalObject.getString("at");

                                // Create a new JsonDataObject with the extracted data
                                JsonDataObject flightData = new JsonDataObject(totalPrice, departureAt, arrivalAt);

                                // Add the new object to the list
                                allFlights.add(flightData);
                            }
                            System.out.println("PHASE 3");


                            System.out.println(allFlights.get(0));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .setNegativeButton("Cancel", (dialog, id) -> AddJourneyDialogFragment.this.getDialog().cancel());

        return builder.create();
    }

    @Override
    public void onAPIResponse(String responseData) {
        // Handle the API response here
        // The responseData parameter contains the JSON response

        // Example: Print the response to the console
        Log.d("API Response", responseData);

        // Example: Parse the JSON response
        try {
            JSONObject responseJson = new JSONObject(responseData);
            // ...
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}