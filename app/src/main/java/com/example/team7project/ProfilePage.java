package com.example.team7project;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfilePage extends AppCompatActivity {

    private TextView profileText, userIdText, emailText;
    public String email, userId, profileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        profileText = findViewById(R.id.profileText);
        userIdText = findViewById(R.id.userIdText);
        emailText = findViewById(R.id.emailText);

        //TODO: get user info from db

    }

}