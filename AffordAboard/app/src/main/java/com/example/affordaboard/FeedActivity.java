package com.example.affordaboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity implements JoinDialogFragment.JoinDialogListener {
    private RecyclerView feedRecyclerView;
    private FeedAdapter feedAdapter;
    private List<FeedItem> feedItems;
    private ImageButton addJourneyButton;
    private ImageButton profileButton;
    private ImageButton historyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        addJourneyButton = findViewById(R.id.addJourneyButton);
        addJourneyButton.setOnClickListener(v -> {
            AddJourneyDialogFragment dialog = new AddJourneyDialogFragment();
            dialog.show(getSupportFragmentManager(), "add_journey_dialog");
        });

        feedItems = new ArrayList<>();

        // Getting the saved/stored data
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = getSharedPreferences("MyApp", MODE_PRIVATE);
        String json = sharedPreferences.getString("feedItems", null);
        Type type = new TypeToken<ArrayList<FeedItem>>() {}.getType();
        feedItems = gson.fromJson(json, type);

        if (feedItems == null) {
            feedItems = new ArrayList<>();
        }

        // feedItems.add(new FeedItem("Jordan Lazov", "Ljubljana - Zurich", "21.03.2023 - 26.03.2023"));
        // feedItems.add(new FeedItem("Aleksa Sibinovic", "Ljubljana - Madeira", "04.04.2023 - 10.04.2023"));
        // feedItems.add(new FeedItem("Anja Kuzevska", "Ljubljana - New Jersey", "19.06.2023 - 01.10.2023"));

        feedRecyclerView = findViewById(R.id.feedRecyclerView);
        feedAdapter = new FeedAdapter(feedItems, this); // Pass the listener here
        feedRecyclerView.setAdapter(feedAdapter);
        feedRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Switching between activities
        profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FeedActivity.this, ProfileActivity.class));
            }
        });

        historyButton = findViewById(R.id.historyButton);
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FeedActivity.this, HistoryActivity.class));
            }
        });
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // Handle positive button click here
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // Handle negative button click here
    }

    public void addNewJourney(String travelLocation, String travelDates, String numOfPeople, String numOfMula) {
        SharedPreferences preferences = getSharedPreferences("MyApp", MODE_PRIVATE);
        String email = preferences.getString("email", null);
        String username = preferences.getString(email + "_username", null);
        FeedItem newFeedItem = new FeedItem(username, travelLocation, travelDates, numOfPeople, numOfMula);
        feedItems.add(newFeedItem);
        feedAdapter.notifyDataSetChanged();
        Gson gson = new Gson();
        String json = gson.toJson(feedItems);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("feedItems", json);

        // Save the current user's FeedItems
        String jsonCurrentUserFeedItems = preferences.getString(email + "_feedItems", null);
        List<FeedItem> currentUserFeedItems;
        if (jsonCurrentUserFeedItems == null) {
            currentUserFeedItems = new ArrayList<>();
        } else {
            Type type = new TypeToken<ArrayList<FeedItem>>() {}.getType();
            currentUserFeedItems = gson.fromJson(jsonCurrentUserFeedItems, type);
        }
        currentUserFeedItems.add(newFeedItem);
        jsonCurrentUserFeedItems = gson.toJson(currentUserFeedItems);
        editor.putString(email + "_feedItems", jsonCurrentUserFeedItems);

        editor.apply();
    }
}