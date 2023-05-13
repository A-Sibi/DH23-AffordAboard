package com.example.affordaboard;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {
    private List<FeedItem> feedItems;
    private JoinDialogFragment.JoinDialogListener listener;

    public FeedAdapter(List<FeedItem> feedItems, JoinDialogFragment.JoinDialogListener listener) {
        this.feedItems = feedItems;
        this.listener = listener;
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

        holder.itemView.setOnClickListener(v -> {
            FragmentManager fm = ((FragmentActivity)v.getContext()).getSupportFragmentManager();
            JoinDialogFragment dialog = new JoinDialogFragment(listener);
            dialog.show(fm, "join_dialog");
        });

        // For deleting items
        holder.itemView.setOnLongClickListener(v -> {
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Delete Journey")
                    .setMessage("Are you sure you want to delete this journey?")
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        feedItems.remove(position);
                        notifyDataSetChanged();
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

            return true;
        });
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
        TextView numOfPeople;
        TextView numOfMula;

        public FeedViewHolder(@NonNull View itemView) {
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