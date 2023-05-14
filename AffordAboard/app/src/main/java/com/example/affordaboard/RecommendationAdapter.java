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

public class RecommendationAdapter extends RecyclerView.Adapter<RecommendationAdapter.RecommendationViewHolder> {
    private List<Recommendation> recommendations;
    private JoinDialogFragment.JoinDialogListener listener;

    public RecommendationAdapter(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
        this.listener = listener;
    }

    @Override
    public RecommendationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommendation_item, parent, false);
        return new RecommendationViewHolder(view);
    }



    @Override
    public void onBindViewHolder(RecommendationViewHolder holder, int position) {
        Recommendation recommendation = recommendations.get(position);

        // Here, you can bind the data to your views
        holder.userName.setText(recommendation.getUserName());
        holder.userName.setText(recommendation.getUserName());
        holder.travelLocation.setText(recommendation.getTravelLocation());
        holder.travelDates.setText(recommendation.getTravelDates());
        holder.numOfPeople.setText(recommendation.getNumOfPeople());
        holder.numOfMula.setText(recommendation.getNumOfMula());

        holder.itemView.setOnClickListener(v -> {
            FragmentManager fm = ((FragmentActivity)v.getContext()).getSupportFragmentManager();
            JoinDialogFragment dialog = new JoinDialogFragment(listener);
            dialog.show(fm, "join_dialog");
        });
    }

    @Override
    public int getItemCount() {
        return recommendations.size();
    }

    public class RecommendationViewHolder extends RecyclerView.ViewHolder {
        // Initialize your views from feed_item.xml here
        TextView userName;
        TextView travelLocation;
        TextView travelDates;
        TextView numOfPeople;
        TextView numOfMula;

        public RecommendationViewHolder(@NonNull View itemView) {
            super(itemView);
            // Find your views and assign them to variables here
            userName = itemView.findViewById(R.id.userName);
            travelLocation = itemView.findViewById(R.id.travelLocation);
            travelDates = itemView.findViewById(R.id.travelDates);
            numOfPeople = itemView.findViewById(R.id.numOfPeople);
            numOfMula = itemView.findViewById(R.id.numOfMula);
        }
    }
}