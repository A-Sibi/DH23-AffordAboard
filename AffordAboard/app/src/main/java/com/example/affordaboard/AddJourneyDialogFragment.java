package com.example.affordaboard;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AddJourneyDialogFragment extends DialogFragment {

    private EditText whereFromEditText;
    private EditText whereToEditText;
    private EditText whenFromEditText;
    private EditText whenToEditText;

    private EditText numOfPeopleEditText;
    private EditText numOfMulaEditText;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_journey, null);

        whereFromEditText = view.findViewById(R.id.whereFromEditText);
        whereToEditText = view.findViewById(R.id.whereToEditText);
        whenFromEditText = view.findViewById(R.id.whenFromEditText);
        whenToEditText = view.findViewById(R.id.whenToEditText);

        numOfPeopleEditText = view.findViewById(R.id.numOfPeopleEditText);
        numOfMulaEditText = view.findViewById(R.id.numOfMulaEditText);

        // Adding a new journey
        builder.setView(view)
                .setPositiveButton("Submit", (dialog, id) -> {
                    String travelLocation = whereFromEditText.getText().toString() + " - " + whereToEditText.getText().toString();
                    String travelDates = whenFromEditText.getText().toString() + " - " + whenToEditText.getText().toString();
                    String numberOfPeople = numOfPeopleEditText.getText().toString();
                    String young_mula_baby = numOfMulaEditText.getText().toString();

                    FeedActivity activity = (FeedActivity) getActivity();
                    activity.addNewJourney(travelLocation, travelDates, numberOfPeople, young_mula_baby);
                })
                .setNegativeButton("Cancel", (dialog, id) -> AddJourneyDialogFragment.this.getDialog().cancel());

        return builder.create();
    }
}