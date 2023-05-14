package com.example.affordaboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    private List<FeedItem> journeyList;

    public ProfileAdapter(List<FeedItem> journeyList) {
        this.journeyList = journeyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeedItem journey = journeyList.get(position);
        FeedItem item = journeyList.get(position);
        // Set the data to the views here
        holder.userName.setText(item.getUserName());
        holder.travelLocation.setText(item.getTravelLocation());
        holder.travelDates.setText(item.getTravelDates());
        holder.numOfPeople.setText(item.getNumOfPeople());
        holder.numOfMula.setText(item.getNumOfMula());
    }

    @Override
    public int getItemCount() {
        return journeyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Initialize your views from feed_item.xml here
        TextView userName;
        TextView travelLocation;
        TextView travelDates;
        TextView numOfPeople;
        TextView numOfMula;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Find your views and assign them to variables here
            userName = itemView.findViewById(R.id.userName);
            travelLocation = itemView.findViewById(R.id.travelLocation);
            travelDates = itemView.findViewById(R.id.travelDates);
            numOfPeople= itemView.findViewById(R.id.numOfPeople);
            numOfMula = itemView.findViewById(R.id.numOfMula);
        }
    }
}
