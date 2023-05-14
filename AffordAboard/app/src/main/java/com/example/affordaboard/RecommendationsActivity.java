package com.example.affordaboard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RecommendationsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecommendationAdapter adapter;
    private List<Recommendation> recommendations;

    private ImageButton profileButton, feedButton, settingsButton, recButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations);

        // Initialize recommendations list
        recommendations = new ArrayList<>();

        // Retrieve user preferences
        SharedPreferences preferences = getSharedPreferences("MyApp", MODE_PRIVATE);
        String email = preferences.getString("email", null);
        int sportspersonScore = preferences.getInt(email + "_sportspersonScore", 0);
        int unexpectedScore = preferences.getInt(email + "_unexpectedScore", 0);
        int comfortScore = preferences.getInt(email + "_comfortScore", 0);

        // Define lists of city types
        List<String> sportsCities = Arrays.asList(
                "Rio de Janeiro", "Los Angeles", "Tokyo", "Barcelona", "Berlin",
                "Sydney", "Beijing", "London", "Athens", "Paris",
                "Rome", "Moscow", "Munich", "Madrid", "Amsterdam",
                "Stockholm", "Montreal", "Seoul", "Helsinki", "Lima",
                "Mexico City", "Toronto", "Buenos Aires", "Cairo", "Nairobi",
                "Johannesburg", "Delhi", "Mumbai", "Bangkok", "Hanoi",
                "Singapore", "Jakarta", "Kuala Lumpur", "Wellington", "Auckland",
                "Vancouver", "Chicago", "Boston", "Miami", "Dallas",
                "Denver", "San Francisco", "Houston", "Dublin", "Vienna",
                "Zurich", "Milan", "Stockholm", "Copenhagen", "Oslo"
        );

        List<String> unexpectedCities = Arrays.asList(
                "Ulaanbaatar", "Ljubljana", "Tirana", "Nur-Sultan", "Skopje",
                "Belgrade", "Kigali", "Baku", "Bishkek", "Tashkent",
                "Ashgabat", "Dushanbe", "Minsk", "Chisinau", "Yerevan",
                "Vientiane", "Phnom Penh", "Windhoek", "Lilongwe", "Gaborone",
                "Bamako", "N'Djamena", "Djibouti City", "Maputo", "Antananarivo",
                "Lusaka", "Kampala", "Harare", "Kinshasa", "Brazzaville",
                "Conakry", "Bujumbura", "Moroni", "Sao Tome", "Victoria",
                "Male", "Suva", "Apia", "Funafuti", "Port Vila",
                "Majuro", "Nuku'alofa", "Palikir", "Castries", "Kingstown",
                "Port of Spain", "St. John's", "Bridgetown", "Roseau", "Nassau"
        );

        List<String> luxuryCities = Arrays.asList(
                "Paris", "New York", "Dubai", "London", "Tokyo",
                "Milan", "Los Angeles", "Cannes", "Geneva", "Monaco",
                "Rome", "Barcelona", "Vienna", "Hong Kong", "Singapore",
                "Miami", "Monte Carlo", "San Francisco", "Sydney", "Marrakech",
                "Zurich", "Amsterdam", "Moscow", "Madrid", "Beijing",
                "Seoul", "Toronto", "Melbourne", "Bangkok", "Istanbul",
                "Abu Dhabi", "Buenos Aires", "Doha", "Las Vegas", "Shanghai",
                "Paris", "New York", "Dubai", "London", "Tokyo",
                "Milan", "Los Angeles", "Cannes", "Geneva", "Monaco"
        );

        // Define lists of first names and last names
        List<String> firstNames = Arrays.asList("Emma", "Liam", "Olivia", "Noah", "Ava", "Isabella",
                "Sophia", "Mia", "Charlotte", "Amelia", "Harper", "Evelyn", "Abigail", "Emily",
                "Elizabeth", "Sofia", "Avery", "Ella", "Scarlett", "Grace", "Chloe", "Victoria",
                "Madison", "Luna", "Penelope", "Layla", "Riley", "Zoey", "Nora", "Lily", "Eleanor",
                "Hannah", "Lillian", "Addison", "Aubrey", "Ellie", "Stella", "Natalie", "Zoe", "Leah", "Hazel");
        List<String> lastNames = Arrays.asList("Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia",
                "Miller", "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson",
                "Anderson", "Thomas", "Taylor", "Moore", "Jackson", "Martin", "Lee", "Perez", "Thompson",
                "White", "Harris", "Sanchez", "Clark", "Ramirez", "Lewis", "Robinson", "Walker", "Young",
                "Allen", "King", "Wright", "Scott", "Torres", "Nguyen", "Hill", "Flores", "Green");

        // Random number generator for generating random user details and city
        Random random = new Random();

        // Generate 20 recommendations
        for (int i = 0; i < 20; i++) {
            // Generate a random username using a random first and last name
            String firstName = firstNames.get(random.nextInt(firstNames.size()));
            String lastName = lastNames.get(random.nextInt(lastNames.size()));
            String userName = firstName + " " + lastName;

            // Select a city list based on the user's preferences
            List<String> cityList;
            if (sportspersonScore > unexpectedScore && sportspersonScore > comfortScore) {
                // If the user's sportsperson score is the highest, select from the sports cities
                cityList = sportsCities;
            } else if (unexpectedScore > sportspersonScore && unexpectedScore > comfortScore) {
                // If the user's unexpected score is the highest, select from the less visited cities
                cityList = unexpectedCities;
            } else {
                // If the user's comfort score is the highest, select from the luxury cities
                cityList = luxuryCities;
            }

            // Generate a random city from the selected list
            String travelLocation = cityList.get(random.nextInt(cityList.size()));

            // Generate other fields
            String travelDates = generateRandomDate(); // You need to implement this method according to your requirement
            String numOfPeople = String.valueOf(random.nextInt(5) + 1);

            // Adjust price based on comfortScore
            int minPrice = 50; // Define a minimum price
            int maxPrice = 10000; // Define a maximum price
            int meanPrice = minPrice + (comfortScore * (maxPrice - minPrice) / 100); // Mean price adjusted based on comfortScore
            int standardDeviation = (maxPrice - meanPrice) / 3; // Standard deviation adjusted based on comfortScore

            // Generate a normally distributed price in the adjusted range
            int price;
            do {
                price = (int) (random.nextGaussian() * standardDeviation + meanPrice); // Generate a normally distributed price
            } while (price < minPrice || price > maxPrice); // Retry if the price is out of range

            String numOfMula = String.valueOf(price);

            // Create a recommendation and add it to the list
            Recommendation recommendation = new Recommendation(userName, travelLocation, travelDates, numOfPeople, numOfMula);
            recommendations.add(recommendation);
        }

        // Set up the RecyclerView
        recyclerView = findViewById(R.id.recRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecommendationAdapter(recommendations);
        recyclerView.setAdapter(adapter);

        // Buttons for switching between activites
        // Buttons for switching between activites
        feedButton = findViewById(R.id.feedButton);
        feedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecommendationsActivity.this, FeedActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        // Switching between activities
        profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecommendationsActivity.this, ProfileActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    private String generateRandomDate() {
        // Define the date format
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        // Define the date range (between now and two years from now)
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = new GregorianCalendar();
        endDate.add(Calendar.YEAR, 2);

        // Generate a random date within the range
        long randomTime = ThreadLocalRandom
                .current()
                .nextLong(startDate.getTimeInMillis(), endDate.getTimeInMillis());

        // Create a date object from the random time and format it as a string
        Calendar randomDate = Calendar.getInstance();
        randomDate.setTimeInMillis(randomTime);
        return format.format(randomDate.getTime());
    }
}