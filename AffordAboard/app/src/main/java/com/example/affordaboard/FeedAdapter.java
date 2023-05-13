package com.example.affordaboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {
    private List<FeedItem> feedItems;

    public FeedAdapter(List<FeedItem> feedItems) {
        this.feedItems = feedItems;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        FeedItem item = feedItems.get(position);
        // Set the data to the views here
        holder.userName.setText(item.getUserName());
        holder.travelLocation.setText(item.getTravelLocation());
        holder.travelDates.setText(item.getTravelDates());
    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder {
        // Initialize your views from feed_item.xml here
        TextView userName;
        TextView travelLocation;
        TextView travelDates;

        public FeedViewHolder(@NonNull View itemView) {
            super(itemView);
            // Find your views and assign them to variables here
            userName = itemView.findViewById(R.id.userName);
            travelLocation = itemView.findViewById(R.id.travelLocation);
            travelDates = itemView.findViewById(R.id.travelDates);
        }
    }
}