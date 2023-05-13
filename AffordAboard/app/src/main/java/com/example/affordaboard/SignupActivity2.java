package com.example.affordaboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;

public class SignupActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        SeekBar sportspersonSeekBar = findViewById(R.id.sportspersonSeekBar);
        SeekBar unexpectedSeekBar = findViewById(R.id.unexpectedSeekBar);
        SeekBar comfortSeekBar = findViewById(R.id.comfortSeekBar);
        CheckBox vehicleCheckbox = findViewById(R.id.vehicleCheckbox);
        // EditText instagramEditText = findViewById(R.id.instagramEditText);

        Button navigationButton2 = findViewById(R.id.signupButton);

        navigationButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sportspersonScore = sportspersonSeekBar.getProgress();
                int unexpectedScore = unexpectedSeekBar.getProgress();
                int comfortScore = comfortSeekBar.getProgress();
                boolean hasVehicle = vehicleCheckbox.isChecked();
                // String instagramLink = instagramEditText.getText().toString();

                // Add logic to store these values or pass them to the next activity

                // Move to the next activity
                startActivity(new Intent(SignupActivity2.this, LoginActivity.class));
            }
        });
    }
}