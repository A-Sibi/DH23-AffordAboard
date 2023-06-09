package com.example.affordaboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

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

                SharedPreferences preferences = getSharedPreferences("MyApp", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                String email = preferences.getString("email", null);
                editor.putInt(email + "_sportspersonScore", sportspersonScore);
                editor.putInt(email + "_unexpectedScore", unexpectedScore);
                editor.putInt(email + "_comfortScore", comfortScore);

                editor.apply();

                // Move to the next activity
                Toast.makeText(SignupActivity2.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignupActivity2.this, LoginActivity.class));
            }
        });
    }
}