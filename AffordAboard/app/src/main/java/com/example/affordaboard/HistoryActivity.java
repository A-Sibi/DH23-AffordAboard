package com.example.affordaboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView historyRecyclerView;
    private HistoryAdapter historyAdapter;
    private List<FeedItem> journeyList;
    private ImageButton profileButton, feedButton, historyButton, settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history); // make sure you have a layout named 'activity_history'

        initializeViews();

        // Retrieve and process the user's journeys
        SharedPreferences preferences = getSharedPreferences("MyApp", MODE_PRIVATE);
        String email = preferences.getString("email", null);
        Gson gson = new Gson();
        String json = preferences.getString(email + "_feedItems", null);
        Type type = new TypeToken<ArrayList<FeedItem>>() {}.getType();
        List<FeedItem> feedItems = gson.fromJson(json, type);

        if (feedItems == null) {
            feedItems = new ArrayList<>();
        }

        // Convert FeedItems to PastJourneys and add to list
        List<FeedItem> journeys = new ArrayList<>();
        for (FeedItem feedItem : feedItems) {
            // Check if the journey's date is before today's date
            String endDate = feedItem.getTravelDates().split(" - ")[1];
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            try {
                Date journeyEndDate = format.parse(endDate);
                Date currentDate = new Date();
                if (journeyEndDate.before(currentDate)) {
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
        historyAdapter = new HistoryAdapter(journeys);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        historyRecyclerView.setAdapter(historyAdapter);

        // Buttons for switching between activities
        feedButton = findViewById(R.id.feedButton);
        feedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HistoryActivity.this, FeedActivity.class));
            }
        });

        profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HistoryActivity.this, ProfileActivity.class));
            }
        });
    }

    private void initializeViews() {
        historyRecyclerView = findViewById(R.id.historyRecyclerView);
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
                if (journeyEndDate.before(currentDate)) {
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
        historyAdapter = new HistoryAdapter(journeys);
        historyRecyclerView.setAdapter(historyAdapter);
    }
}