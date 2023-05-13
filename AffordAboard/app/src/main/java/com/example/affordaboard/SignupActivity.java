package com.example.affordaboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // To make the logo look like it's breathing
        // ImageView rectangleBackground = findViewById(R.id.rectangleBackground);
        // Animation breathingAnimation = AnimationUtils.loadAnimation(this, R.anim.breathing_animation);
        // rectangleBackground.startAnimation(breathingAnimation);

        // For the signup button to switch to LoginActivity
        Button navigationButton = findViewById(R.id.secondSignupPageButton);

        navigationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ((EditText) findViewById(R.id.usernameEditText)).getText().toString();
                String email = ((EditText) findViewById(R.id.emailEditText)).getText().toString();
                String password = ((EditText) findViewById(R.id.passwordEditText)).getText().toString();
                String confirmPassword = ((EditText) findViewById(R.id.confirmPasswordEditText)).getText().toString();
                String age = ((EditText) findViewById(R.id.ageEditText)).getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
                    Toast.makeText(SignupActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences preferences = getSharedPreferences("MyApp", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(email + "_username", username);
                    editor.putString(email + "_password", password);
                    editor.putString(email + "_age", age);
                    editor.apply();
                    // Toast.makeText(SignupActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignupActivity.this, SignupActivity2.class));
                }
            }
        });
    }
}