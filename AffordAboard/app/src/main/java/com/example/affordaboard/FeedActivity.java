package com.example.affordaboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity implements JoinDialogFragment.JoinDialogListener {
    private RecyclerView feedRecyclerView;
    private FeedAdapter feedAdapter;
    private List<FeedItem> feedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        feedItems = new ArrayList<>();
        // populate the list
        feedItems.add(new FeedItem("Jordan Lazov", "Zurich", "21.03.2023 - 26.03.2023"));
        feedItems.add(new FeedItem("Aleksa Sibinovic", "Madeira", "04.04.2023 - 10.04.2023"));
        feedItems.add(new FeedItem("Anja Kuzevska", "New Jersey", "19.06.2023 - 01.10.2023"));

        feedRecyclerView = findViewById(R.id.feedRecyclerView);
        feedAdapter = new FeedAdapter(feedItems, this); // Pass the listener here
        feedRecyclerView.setAdapter(feedAdapter);
        feedRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // Handle positive button click here
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // Handle negative button click here
    }
}