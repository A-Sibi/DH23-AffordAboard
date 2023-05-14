package com.example.affordaboard;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class API {

    public interface APICallback {
        void onAPIResponse(String responseData);
    }
    public static class APICallTask  extends AsyncTask<Void, Void, String> {
        private String departureLocation;
        private String destinationLocation;
        private String departureDate;
        private String returnDate;
        private int adults;
        private int maxPrice;
        private APICallback callback;
        private String jsonString;

        public APICallTask(String departureLocation, String destinationLocation, String departureDate,
                           String returnDate, int adults, int maxPrice, APICallback callback) {
            this.departureLocation = departureLocation;
            this.destinationLocation = destinationLocation;
            this.departureDate = departureDate;
            this.returnDate = returnDate;
            this.adults = adults;
            this.maxPrice = maxPrice;
            this.callback = callback;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                // Set the API endpoint URL for token retrieval
                String tokenUrl = "https://test.api.amadeus.com/v1/security/oauth2/token";

                // Set your API Key and API Secret
                String apiKey = "IyjZwiAhHccwNkDTTpOB3y2tbmz2t61T";
                String apiSecret = "qhJv8liXdFeR4Rsq";

                // Set the required parameters for token retrieval
                String payload = "grant_type=client_credentials&client_id=" + apiKey + "&client_secret=" + apiSecret;

                // Send the POST request to retrieve the access token
                String accessToken = sendPostRequest(tokenUrl, payload);
                Log.d("Access Token", accessToken);

                // Now you can use the access token in subsequent API requests
                // Set the API endpoint URL for flight-offers-search
                String flightOffersUrl = "https://test.api.amadeus.com/v2/shopping/flight-offers";

                // Set the required query parameters for flight-offers-search
                String params = "originLocationCode=" + departureLocation +
                        "&destinationLocationCode=" + destinationLocation +
                        "&departureDate=" + departureDate +
                        "&returnDate=" + returnDate +
                        "&adults=" + adults +
                        "&maxPrice=" + maxPrice;


                JSONObject token = new JSONObject(accessToken);
                // Set the Authorization header with the access token
                String authorizationHeader = "Bearer " + token.getString("access_token");
                System.out.println(authorizationHeader);

                // Make the API request for flight-offers-search
                String responseData = sendGetRequest(flightOffersUrl + "?" + params, authorizationHeader);
                Log.d("Response Data", responseData);
                this.jsonString = responseData;
                return responseData;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public String getJsonString() {
            return this.jsonString;
        }

        @Override
        protected void onPostExecute(String responseData) {
            if (callback != null) {
                callback.onAPIResponse(responseData);
            }
        }

        private static String sendPostRequest(String url, String payload) throws IOException {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                // Create the connection
                URL requestUrl = new URL(url);
                connection = (HttpURLConnection) requestUrl.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                // Write the payload to the connection's output stream
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(payload.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                // Read the response from the connection's input stream
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            } finally {
                // Close the connections and reader
                if (connection != null) {
                    connection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private static String sendGetRequest(String url, String authorizationHeader) throws IOException {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                // Create the connection
                URL requestUrl = new URL(url);
                connection = (HttpURLConnection) requestUrl.openConnection();
                connection.setRequestProperty("Authorization", authorizationHeader);
                connection.setDoOutput(false);
                connection.setDoInput(true);
                // Set the request method
                connection.setRequestMethod("GET");

                // Read the response from the connection's input stream
                if (connection.getResponseCode() == 200) {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                }
                else {
                    reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                }
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            } finally {
                // Close the connections and reader
                if (connection != null) {
                    connection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}