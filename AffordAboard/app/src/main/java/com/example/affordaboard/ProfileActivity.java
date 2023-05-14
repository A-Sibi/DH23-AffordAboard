package com.example.affordaboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private TextView textViewTitle, textViewName, textViewCurrentLocation, textViewAge, textViewType;
    private ImageView imageViewProfile;
    private Button buttonChangePicture, buttonEditProfile;
    private RecyclerView profileRecyclerView;
    private ProfileAdapter profileAdapter;
    private List<FeedItem> journeyList;
    private ImageButton profileButton, feedButton, settingsButton, recButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initializeViews();

        // Load and set the profile picture
        SharedPreferences preferences = getSharedPreferences("MyApp", MODE_PRIVATE);
        String email = preferences.getString("email", null);

        // Set user data
        textViewCurrentLocation.setText("Ljubljana, Slovenia");
        textViewType.setText("Very active");

        // Retrieve and process the user's journeys
        preferences = getSharedPreferences("MyApp", MODE_PRIVATE);
        email = preferences.getString("email", null);
        Gson gson = new Gson();
        String json = preferences.getString(email + "_feedItems", null);
        Type type = new TypeToken<ArrayList<FeedItem>>() {}.getType();
        List<FeedItem> feedItems = gson.fromJson(json, type);

        // Setting the profile info according to the user
        textViewName.setText(preferences.getString(email + "_username", null));
        textViewAge.setText(preferences.getString(email + "_age", null));

        if (feedItems == null) {
            feedItems = new ArrayList<>();
        }

        // Convert FeedItems to UpcomingJourneys and add to list
        List<FeedItem> journeys = new ArrayList<>();
        for (FeedItem feedItem : feedItems) {
            // Check if the journey's date is after today's date
            String endDate = feedItem.getTravelDates().split(" - ")[1];
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            try {
                Date journeyEndDate = format.parse(endDate);
                Date currentDate = new Date();
                if (journeyEndDate.after(currentDate)) {
                    String username = preferences.getString(email + "_username", null);
                    String destination = feedItem.getTravelLocation();
                    String dates = feedItem.getTravelDates();
                    String numOfPeople = feedItem.getNumOfPeople();
                    String numOfMula = feedItem.getNumOfMula();
                    journeys.add(new FeedItem(username, destination, dates, numOfPeople, numOfMula));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // Set RecyclerView
        profileAdapter = new ProfileAdapter(journeys);
        profileRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        profileRecyclerView.setAdapter(profileAdapter);

        // Buttons for switching between activites
        feedButton = findViewById(R.id.feedButton);
        feedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, FeedActivity.class));
            }
        });

        // Switching between activities
        recButton = findViewById(R.id.recommendationsButton);
        recButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, RecommendationsActivity.class));
            }
        });

        // Switching between activities
        buttonEditProfile = findViewById(R.id.buttonEditProfile);
        buttonEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, EditStatsActivity.class));
            }
        });
    }

    private void initializeViews() {
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewName = findViewById(R.id.textViewName);
        textViewCurrentLocation = findViewById(R.id.textViewCurrentLocation);
        textViewAge = findViewById(R.id.textViewAge);
        textViewType = findViewById(R.id.textViewType);
        imageViewProfile = findViewById(R.id.imageViewProfile);
        buttonChangePicture = findViewById(R.id.buttonChangePicture);
        buttonEditProfile = findViewById(R.id.buttonEditProfile);
        profileRecyclerView = findViewById(R.id.profileRecyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences preferences = getSharedPreferences("MyApp", MODE_PRIVATE);
        String email = preferences.getString("email", null);
        Gson gson = new Gson();
        String json = preferences.getString(email + "_feedItems", null);
        Type type = new TypeToken<ArrayList<FeedItem>>() {}.getType();
        List<FeedItem> feedItems = gson.fromJson(json, type);

        if (feedItems == null) {
            feedItems = new ArrayList<>();
        }

        List<FeedItem> journeys = new ArrayList<>();
        for (FeedItem feedItem : feedItems) {
            String endDate = feedItem.getTravelDates().split(" - ")[1];
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            try {
                Date journeyEndDate = format.parse(endDate);
                Date currentDate = new Date();
                if (journeyEndDate.after(currentDate)) {
                    String username = preferences.getString(email + "_username", null);
                    String destination = feedItem.getTravelLocation();
                    String dates = feedItem.getTravelDates();
                    String numOfPeople = feedItem.getNumOfPeople();
                    String numOfMula = feedItem.getNumOfMula();
                    journeys.add(new FeedItem(username, destination, dates, numOfPeople, numOfMula));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // Set RecyclerView
        profileAdapter = new ProfileAdapter(journeys);
        profileRecyclerView.setAdapter(profileAdapter);
    }


}
