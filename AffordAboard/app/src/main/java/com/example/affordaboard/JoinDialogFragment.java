package com.example.affordaboard;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class JoinDialogFragment extends DialogFragment {
    // Define view variables here
    private CheckBox vehicleCheckbox;
    private CheckBox joinCheckbox;
    private EditText daysEditText;
    private CheckBox expensesCheckbox;
    private EditText peopleEditText;
    private EditText healthEditText;
    private EditText numberEditText;


    private JoinDialogListener listener;

    public interface JoinDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    public JoinDialogFragment(JoinDialogListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_join, null);

        // Initialize your views here
        vehicleCheckbox = view.findViewById(R.id.vehicle_checkbox);
        joinCheckbox = view.findViewById(R.id.join_checkbox);
        daysEditText = view.findViewById(R.id.days_edit_text);
        expensesCheckbox = view.findViewById(R.id.expenses_checkbox);
        peopleEditText = view.findViewById(R.id.people_edit_text);
        healthEditText = view.findViewById(R.id.health_edit_text);
        numberEditText = view.findViewById(R.id.number_edit_text);
        // ... other views ...

        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // handle the data here
                        boolean hasVehicle = vehicleCheckbox.isChecked();
                        boolean willJoin = joinCheckbox.isChecked();
                        String availableDays = daysEditText.getText().toString();
                        boolean willShareExpenses = expensesCheckbox.isChecked();
                        String numberOfPeople = peopleEditText.getText().toString();
                        // ... handle other data ...

                        // TODO: Handle the data
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        JoinDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}

